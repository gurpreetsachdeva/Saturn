package com.ms.qaTools.io.rowSource.file

import java.io.File
import java.io.FileReader
import java.io.{ Reader => JReader }
import java.io.StringReader

import scala.collection.AbstractIterator
import scala.collection.JavaConversions.asScalaIterator
import scala.language.implicitConversions
import scala.slick.util.CloseableIterator

import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration

import com.ms.qaTools.AnyUtil
import com.ms.qaTools.io.rowSource.ColumnDefinition
import com.ms.qaTools.io.rowSource.ColumnDefinitions

class PropertiesRowSource(prop: Configuration) extends AbstractIterator[Seq[String]] with ColumnDefinitions {
  val colDefs = ColumnDefinition.fromColumnNames(prop.getKeys.toIterator.asInstanceOf[Iterator[String]]/* make the compiler happy ... */.toSeq.sorted)
  protected val row: Seq[String] = colDefs.map{c => prop.getString(c.name)}
  var read = false
  def hasNext = !read
  def next = if (hasNext) {read = true; row} else Iterator.empty.next
}

object PropertiesRowSource {
  def apply(reader: JReader): PropertiesRowSource =
    new PropertiesRowSource(Properties(reader)) with CloseableIterator[Seq[String]] {
      def close() = reader.close()
    }
}

object PropertiesFileRowSource {
  def apply(fileName: String) = PropertiesRowSource(new FileReader(fileName))
  def apply(file: File) = PropertiesRowSource(new FileReader(file))
}

object PropertiesBufferRowSource {
  def apply(content: String) = PropertiesRowSource(new StringReader(content))
}

object Properties {
  def apply(reader: JReader): Configuration = {
    new PropertiesConfiguration().withSideEffect { p =>
      p.setDelimiterParsingDisabled(true)
      p.load(reader)
    }.interpolatedConfiguration
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
