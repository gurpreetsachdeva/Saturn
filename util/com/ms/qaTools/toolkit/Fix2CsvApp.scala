package com.ms.qaTools.toolkit

import com.ms.qaTools.fix.FixExtractor
import com.ms.qaTools.toolkit.cmdLine.BasicCmdLine
import com.ms.qaTools.toolkit.cmdLine.FixDictionary
import com.ms.qaTools.toolkit.cmdLine.InputFixFile
import com.ms.qaTools.toolkit.cmdLine.OutputDelimitedFile
import com.ms.qaTools.toolkit.cmdLine.RequiredConfigFile

trait MessageType {
  @org.kohsuke.args4j.Option(name = "--messageType", usage = "FIX message type", required = true)
  val messageType: String = null
}

abstract class AFix2CsvApp extends ToolkitApp[Result] {
  val APP_NAME = "Fix2Csv"
  def cmdLine: BasicCmdLine with InputFixFile with OutputDelimitedFile with RequiredConfigFile with MessageType

  exit(util.Try {
    parseArguments
    cmdLine.delimitedRowWriter.write(FixExtractor(
      cmdLine.inRowSource,
      Xml2Csv.parse(new java.io.FileReader(cmdLine.configFileName), Xml2Csv.xPathSplitRegex).toSeq,
      cmdLine.dataDictionary,
      cmdLine.messageType))
    Result(Passed)})
}

object Fix2CsvApp extends AFix2CsvApp {
  lazy val cmdLine = new BasicCmdLine with InputFixFile with OutputDelimitedFile with RequiredConfigFile with MessageType with FixDictionary
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
