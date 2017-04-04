package com.ms.qaTools.io.rowSource.delimited

import java.io.StringReader
import java.io.{ Reader => JReader }
import scala.annotation.tailrec
import java.io.Reader
import java.io.PushbackReader
import scala.util.matching.Regex
import scala.util.parsing.input.{Position, NoPosition}
import java.io.Closeable
import com.ms.qaTools.io.rowSource.internal.StreamingReader

/**
 * Simple CSV parser as per http://tools.ietf.org/html/rfc4180
 *
 */

class DelimitedParser(
  reader: Reader,
  separator: String,
  newline: String,
  bufSize: Int,
  quoteCharOpt: Option[Char],
  escapeCharOpt: Option[Char],
  isEmptyStringNotNull: Boolean) extends Parser {
  require((quoteCharOpt.isDefined && escapeCharOpt.isDefined) || (!quoteCharOpt.isDefined && !escapeCharOpt.isDefined), "Quote and escape character must be both set or unset.")

  def this(
    reader: Reader,
    separator: Char = ',',
    newline: Char = '\n',
    bufSize: Int = 4 * 1024,
    quoteChar: Option[Char] = Some('"'),
    escapeChar: Option[Char] = Some('"'),
    isEmptyStringNotNull: Boolean = true) = {
    this(reader, separator.toString, newline.toString, bufSize, quoteChar, escapeChar, isEmptyStringNotNull)
  }

  val streamingReader = new StreamingReader(reader, bufSize)
  val quotable  = quoteCharOpt.isDefined && escapeCharOpt.isDefined
  lazy val quoteChar = quoteCharOpt.getOrElse(throw new Error("Quote character not defined."))
  lazy val escapeChar = escapeCharOpt.getOrElse(throw new Error("Escape character not defined."))

  private def headIs(buffer: StreamingReader, char: Option[Char]) = char.map(c => buffer.headIs(c)).getOrElse(false)

  @tailrec
  private def parseCell(buffer: StreamingReader, atHead: Boolean): Option[String] = {
    def isWhitespace = buffer.first.isWhitespace      &&
                       !headIs(buffer, quoteCharOpt)  &&
                       !headIs(buffer, escapeCharOpt) &&
                       !buffer.headIs(separator)      &&
                       !buffer.headIs(newline)

    @tailrec
    def readUntil0(inQuotes: Boolean = false, accu: StringBuilder = new StringBuilder(bufSize)): StringBuilder = {
      def head2is(chars: Seq[Char]) = buffer.first(2) == chars.mkString
      def isNextCharEscapable = inQuotes && !buffer.atEnd && { head2is(Seq(escapeChar, quoteChar)) || head2is(Seq(escapeChar, escapeChar)) }
      def isNextCharEscapedQuote = inQuotes && !buffer.atEnd && (head2is(Seq(quoteChar, quoteChar)))
      def isSeparator = !inQuotes && buffer.headIs(separator)
      def isNewLine = !inQuotes && buffer.headIs(newline)
      val c = buffer.first
      if (quotable && ((c == escapeChar && isNextCharEscapable) || (c == quoteChar && isNextCharEscapedQuote))) {
        buffer.drop
        readUntil0(inQuotes, accu.append(buffer.drop))
      }
      else if (quotable && c == quoteChar && !buffer.atEnd) {
        if (inQuotes) {
          buffer.drop
          while (isWhitespace) buffer.drop
          if (!(buffer.atEnd || buffer.headIs(separator) || buffer.headIs(newline)))
            throw new ParsingError("Separator or newline expected but '\\u%04X' found".format(buffer.first.toInt),
              buffer.pos)
          accu
        }
        else
          throw new ParsingError("Quoted parts behind another quoted or non-quoted parts in a single field", buffer.pos)
      }
      else if (isSeparator || isNewLine || buffer.atEnd) {
        if (inQuotes) throw new ParsingError("Odd number of quoting character(s)", NoPosition)
        accu
      }
      else
        readUntil0(inQuotes, accu.append(buffer.drop))
    }

    if (buffer.headIs(separator)) {
      if (atHead) None
      else {
        buffer.drop(separator.length)
        if (buffer.atEnd || buffer.headIs(separator)) None
        else parseCell(buffer, false)
      }
    }
    else if (isWhitespace) {
      buffer.drop
      if (buffer.headIs(separator)) None 
      else parseCell(buffer, false)
    }
    else if (headIs(buffer, quoteCharOpt)) {
      buffer.drop
      readUntil0(inQuotes = true).toString match {
        case "" if !isEmptyStringNotNull => None
        case s                           => Some(s)
      }
    }
    else {
      readUntil0().toString.trim match {
        case "" => None
        case s  => Some(s)
      }
    }
  }

  def readNext: Seq[Option[String]] = parseLine(streamingReader, true).map {_.reverse}.orNull

  @tailrec
  private final def parseLine(buffer: StreamingReader, atHead: Boolean, accu: Seq[Option[String]] = Seq()): Option[Seq[Option[String]]] = {
    def processEnd0(onEmpty: Option[Seq[Option[String]]]) =
      if (accu.isEmpty) onEmpty
      else Some(accu)
    if (buffer.atEnd) processEnd0(None)
    else {
      buffer.first(newline.length) match {
        case `newline` => {
          buffer.drop(newline.length)
          processEnd0(Some(Seq(None)))
        }
        case _ => {
          parseLine(buffer, false, parseCell(buffer, atHead) +: accu)
        }
      }
    }
  }
}

object DelimitedParser {
  val DEFAULT_SEPARATOR = ','
  val DEFAULT_QUOTE_CHARACTER = '"'
  val DEFAULT_ESCAPE_CHARACTER = '"'
  val DEFAULT_SKIP_LINES = 0

  def apply(reader: Reader,
            separator: String = DEFAULT_SEPARATOR.toString,
            newline: String = "\n",
            bufSize: Int = 4 * 1024,
            quoteChar: Option[Char] = Some(DEFAULT_QUOTE_CHARACTER),
            escapeChar: Option[Char] = Some(DEFAULT_ESCAPE_CHARACTER),
            isEmptyStringNotNull: Boolean = true) =
    new DelimitedParser(reader, separator, newline, bufSize, quoteChar, escapeChar, isEmptyStringNotNull)
}

case class ParsingError(msg: String, pos: Position) extends Exception(msg) {
  override def getMessage = s"$msg at [$pos]"
}
/*
COPYRIGHT NOTICE AND DISCLAIMER
Copyright (c) 2009-2016, Contributor

This program is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License, version 3, as published by the Free Software Foundation.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License, version 3 for more details.

You should have received a copy of the GNU Lesser General Public License, version 3, along with this program; if not, see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
THE FOLLOWING DISCLAIMER APPLIES TO ALL SOFTWARE CODE AND OTHER MATERIALS CONTRIBUTED IN CONNECTION WITH THIS PROGRAM:
THIS SOFTWARE IS LICENSED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE AND ANY WARRANTY OF NON-INFRINGEMENT, ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. THIS SOFTWARE MAY BE REDISTRIBUTED TO OTHERS ONLY BY EFFECTIVELY USING THIS OR ANOTHER EQUIVALENT DISCLAIMER AS WELL AS ANY OTHER LICENSE TERMS THAT MAY APPLY.
*/