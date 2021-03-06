package com.ms.qaTools.saturn.codeGen.notifier.report.generators

import java.io.ByteArrayOutputStream
import java.io.PrintStream

import scala.Option.option2Iterable

import org.jsoup.nodes.Element

import com.ms.qaTools.saturn.codeGen.IterationResult
import com.ms.qaTools.saturn.codeGen.notifier.console.printMetaDataContext
import com.ms.qaTools.saturn.codeGen.notifier.console.printMetaDatas
import com.ms.qaTools.saturn.runtime.SaturnExecutionContext
import com.ms.qaTools.saturn.runtime.notifier.html.appendException
import com.ms.qaTools.saturn.runtime.notifier.html.htmlSpaces
import com.ms.qaTools.saturn.runtime.notifier.html.string2Html
import com.ms.qaTools.toolkit.dsCompare.DsCompareResult

case class DataCompareHtmlGenerator(implicit sc: SaturnExecutionContext) extends BasicHtmlGenerator {
  override def runGroupIconClassName: String = "dataCompareIcon"
  override def runGroupType: String = "DataCompare"

  override def generateIterationResult(name: String, fullName: String, result: IterationResult[Any], parentElement: Element, asScenario: Boolean = false, displayIterationNo: Boolean = true): Unit = {
    result.moduleResult match {
      case DsCompareResult(status, exception, counter) =>
        for (e <- exception) appendException(parentElement, e, 0)(sc.outputVerbosity)

        val outputStream = new ByteArrayOutputStream
        val metaDataContexts = result.context.metaDataContexts
        for (metaData <- Seq("Left", "Right", "Output")) {
          printMetaDatas(metaDataContexts, Some(metaData), 0, new PrintStream(outputStream))(true)
          parentElement.append(string2Html(new String(outputStream.toByteArray)) + "<br/>")
          outputStream.reset
        }

        parentElement.append("Summary:<br/>")
        parentElement.append(htmlSpaces(1) + "Left: %s<br/>"          .format(counter.left))
        parentElement.append(htmlSpaces(1) + "Right: %s<br/>"         .format(counter.right))
        parentElement.append(htmlSpaces(1) + "Different: %s<br/>"     .format(counter.different))
        parentElement.append(htmlSpaces(1) + "In Left Only: %s<br/>"  .format(counter.inLeftOnly))
        parentElement.append(htmlSpaces(1) + "In Right Only: %s<br/>" .format(counter.inRightOnly))
        parentElement.append(htmlSpaces(1) + "Explained: %s<br/>"     .format(counter.explained))
        parentElement.append(htmlSpaces(1) + "Identical: %s<br/>"     .format(counter.identical))

        outputStream.close()
      case r => throw new Exception(s"DataCompareHtmlGenerator cannot handle this type of result: ${r.getClass.getName}")
    }
  }
}
/*
Copyright 2017 Morgan Stanley

Licensed under the GNU Lesser General Public License Version 3 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

https://www.gnu.org/licenses/lgpl-3.0.en.html

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

IN ADDITION, THE FOLLOWING DISCLAIMER APPLIES IN CONNECTION WITH THIS PROGRAM:

THIS SOFTWARE IS LICENSED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE AND ANY
WARRANTY OF NON-INFRINGEMENT, ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
OF SUCH DAMAGE. THIS SOFTWARE MAY BE REDISTRIBUTED TO OTHERS ONLY BY EFFECTIVELY
USING THIS OR ANOTHER EQUIVALENT DISCLAIMER IN ADDITION TO ANY OTHER REQUIRED
LICENSE TERMS.
*/
