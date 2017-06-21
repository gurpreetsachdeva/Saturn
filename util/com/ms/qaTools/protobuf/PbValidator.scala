package com.ms.qaTools.protobuf
import com.google.protobuf.Descriptors.Descriptor
import com.google.protobuf.Descriptors.FieldDescriptor
import com.google.protobuf.DynamicMessage
import com.google.protobuf.Message
import com.google.protobuf.TextFormat
import com.ms.qaTools.AnyUtil
import com.ms.qaTools.io.rowSource.ColumnDefinition
import com.ms.qaTools.io.rowSource.IndexedRepresentation
import com.ms.qaTools.io.rowSource.ProtoBufPathRowSource
import com.ms.qaTools.toolkit.PBValidateComparator
import com.ms.qaTools.tree.PBNode
import com.ms.qaTools.tree.TreeNode
import com.ms.qaTools.tree.validator.Comparator
import com.ms.qaTools.tree.validator.IndexedDiffSet
import com.ms.qaTools.Validator
import org.apache.commons.codec.binary.Base64
import com.ms.qaTools.IteratorProxy

case class PbValidator(configTemplate: Option[DynamicMessage], expected: Iterator[Message], actual: Iterator[Message], descriptor: Descriptor)
  extends Validator[Message, FieldDescriptor](expected, actual, configTemplate.map { PBValidateComparator(_, descriptor) }.getOrElse(PBValidateComparator())) {
  protected def asPathRowSource(i: Iterator[Message]) = ProtoBufPathRowSource(keys.zipWithIndex.map { z => (z._1, "COL" + z._2) }, i).asIndexedRepresentationIterator()
  protected val fromSeqString = (r: Seq[String]) =>
    new IndexedRepresentation[Message] {
      val indexed = r.tail
      val colDefs = ColumnDefinition.fromColumnNames(keys)
      val representation = {
        val b = DynamicMessage.newBuilder(descriptor)
        TextFormat.merge(Base64.decodeBase64(r.head.getBytes).map(_.toChar).mkString, b)
        b.build
      }
      def prettyPrint = Base64.encodeBase64(representation.toString.getBytes).map(_.toChar).mkString
    }
  protected val toSeqString =
    (r: IndexedRepresentation[Message]) => Base64.encodeBase64(r.representation.toString.getBytes).map(_.toChar).mkString :: r.indexed.toList
  val self = new IndexedDiffSet[Message, FieldDescriptor] {
    val Seq(left, right) = for (i <- Seq(expected, actual)) yield sort(asPathRowSource(i), fromSeqString, toSeqString)
    def inflate(x: Message) = Option(PBNode(x))
    def inflateLeft(x: Message) = inflate(x)
    def inflateRight(x: Message) = inflate(x)
    def comparator = validator.comparator
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
