package com.ms.qaTools.saturn.codeGen

import scala.collection.JavaConversions.asScalaBuffer
import scala.util.Try

import com.ms.qaTools.MonadSeqUtil
import com.ms.qaTools.codeGen.scala.FutureGen
import com.ms.qaTools.codeGen.scala.OptionExpr
import com.ms.qaTools.codeGen.scala.ScalaExpr
import com.ms.qaTools.codeGen.scala.ScalaGen
import com.ms.qaTools.codeGen.scala.SeqTryExpr
import com.ms.qaTools.codeGen.scala.TryExpr
import com.ms.qaTools.codeGen.scala.TryGen
import com.ms.qaTools.saturn.{XmlManipStep => MXmlManipStep}
import com.ms.qaTools.saturn.modules.xmlManipModule.AddOperation
import com.ms.qaTools.saturn.modules.xmlManipModule.DeleteOperation
import com.ms.qaTools.saturn.modules.xmlManipModule.ExtractOperation
import com.ms.qaTools.saturn.modules.xmlManipModule.ReplaceOperation
import com.ms.qaTools.saturn.modules.xmlManipModule.SplitOperation
import com.ms.qaTools.saturn.modules.xmlManipModule.WhereOperation

object XmlManipLogicGenerator {

  def apply(xmlManipStep: MXmlManipStep)(implicit codeGenUtil: SaturnCodeGenUtil): Try[FutureGen] = {
    implicit val appendOptions = ContextAppendOptions(true)
    for {
      config <- Try { val c = xmlManipStep.getXmlManipConfiguration(); if (c == null) throw new NullPointerException(s"Configuration for XmlManip Step: $xmlManipStep cannot be null.") else c }
      inputIOGen <- ResourceGenerator(config.getInputResource())
      outputIOGen <- ResourceGenerator(config.getOutputResource())
      namespaceMap <- {
        val namespaces = config.getNamespaces().filter(_.isEnabled())
        namespaces.map { ns =>
          for {
            prefix <- ComplexValueStringGenerator(ns.getPrefix())
            uri <- ComplexValueStringGenerator(ns.getUri())
          } yield (prefix, uri)
        }.toTrySeq
      }
      operationsGen <- config.getOperations().filter(_.isEnabled()).map { op =>
        op match {
          case op: AddOperation     => genXmlManipAddOperation(op)
          case op: DeleteOperation  => genXmlManipDeleteOperation(op)
          case op: ReplaceOperation => genXmlManipReplaceOperation(op)
          case op: ExtractOperation => genXmlManipExtractOperation(op)
          case op: SplitOperation   => genXmlManipSplitOperation(op)
          case op: WhereOperation   => genXmlManipWhereOperation(op)
        }
      }.toTrySeq.map { ops => if (ops.isEmpty) ScalaExpr("Seq[Try[XmlNodeTransformer[_, _]]]()") else SeqTryExpr(ops) }
    } yield StepLogicGenerator.modifierGen(new ScalaGen {
      override def generate(): Try[String] = for {
        inputStr <- inputIOGen.generate
        outputStr <- outputIOGen.generate
        namespaceStr <- namespaceMap.map {
          case (prefixGen, uriGen) => prefixGen.generate flatMap (prefix => uriGen.generate map (uri => s"($prefix, $uri)"))
        }.toTrySeq
        operationsStr <- operationsGen.generate
      } yield s"""XmlManipRunner(context,
                                 $inputStr,
                                 $outputStr,
                                 ${namespaceStr.mkString("Seq[(Try[String], Try[String])](", ",", ")")},
                                 ${operationsStr})"""
    }, xmlManipStep, "XmlManip")
  }

  def genXmlManipAddOperation(op: AddOperation): Try[TryGen] =
    for {
      nodeNameGen <- if (!op.isIsCData() && !op.isIsXml()) ComplexValueStringGenerator(op.getNodeName()) else Try { TryExpr("\"\"") }
      valueGen <- ComplexValueStringGenerator(op.getValue())
      parentGen <- Option(op.getParentNodeName()) match {
        case Some(name) => ComplexValueStringGenerator(name).map(OptionExpr(_))
        case _          => Try { ScalaExpr("None") }
      }
      uriGen <- Option(op.getUri()) match {
        case Some(name) => ComplexValueStringGenerator(name).map(OptionExpr(_))
        case _          => Try { ScalaExpr("None") }
      }
    } yield new TryGen {
      override def generate(): Try[String] = for {
        nodeNameStr <- nodeNameGen.generate
        valueStr <- valueGen.generate
        parentStr <- parentGen.generate
        uriStr <- uriGen.generate
      } yield s"""XmlAddMapper(context, $nodeNameStr, $valueStr, $parentStr, $uriStr, ${op.isIsAttribute()}, ${op.isIsCData()}, ${op.isIsXml()})"""
    }

  def genXmlManipDeleteOperation(op: DeleteOperation): Try[TryGen] =
    for {
      xPathsGen <- op.getXPaths().filter(_.isEnabled()).map { xPath => ComplexValueStringGenerator(xPath) }.toTrySeq.map { SeqTryExpr(_) }
    } yield new TryGen {
      override def generate(): Try[String] = for {
        xPathsStr <- xPathsGen.generate
      } yield s"""XmlDeleteMapper(context, $xPathsStr)"""
    }

  def genXmlManipReplaceOperation(op: ReplaceOperation): Try[TryGen] =
    for {
      searchGen <- ComplexValueStringGenerator(op.getSearch())
      replaceGen <- ComplexValueStringGenerator(op.getWith())
    } yield new TryGen {
      override def generate(): Try[String] = for {
        searchStr <- searchGen.generate
        replaceStr <- replaceGen.generate
      } yield s"""XmlReplaceMapper(context, $searchStr, $replaceStr)"""
    }

  def genXmlManipExtractOperation(op: ExtractOperation): Try[TryGen] =
    for {
      xPathGen <- ComplexValueStringGenerator(op.getXPath())
    } yield new TryGen {
      override def generate(): Try[String] = for {
        xPathStr <- xPathGen.generate
      } yield s"""XmlExtractMapper(context, $xPathStr)"""
    }

  def genXmlManipSplitOperation(op: SplitOperation): Try[TryGen] = {
    implicit val appendOptions = ContextAppendOptions(true, (_) => "SplitOutput")
    for {
      keysGen <- op.getKeys().map { key => ComplexValueStringGenerator(key) }.toTrySeq.map { SeqTryExpr(_) }
      outputIOGen <- ResourceGenerator(op.getOutputResource())
    } yield new TryGen {
      override def generate(): Try[String] = for {
        keysStr <- keysGen.generate
        outputIOStr <- outputIOGen.generate
      } yield s"""XmlSplitMapper(context, $keysStr, $outputIOStr)"""
    }
  }

  def genXmlManipWhereOperation(op: WhereOperation): Try[TryGen] =
    for {
      xPathGen <- ComplexValueStringGenerator(op.getXPath())
    } yield new TryGen {
      override def generate(): Try[String] = for {
        xPathStr <- xPathGen.generate
      } yield s"""XmlWhereMapper(context, $xPathStr, ${op.isExclude()})"""
    }

}