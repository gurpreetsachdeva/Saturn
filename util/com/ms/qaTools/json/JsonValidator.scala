package com.ms.qaTools.json

import com.fasterxml.jackson.databind.JsonNode
import com.ms.qaTools.Validator
import com.ms.qaTools.io.rowSource.ColumnDefinition
import com.ms.qaTools.io.rowSource.IndexedRepresentation
import com.ms.qaTools.io.rowSource.JsonPathRowSource
import com.ms.qaTools.tree
import com.ms.qaTools.tree.validator.Comparator
import com.ms.qaTools.tree.validator.ComparatorWithKeys
import com.ms.qaTools.tree.validator.IndexedDiffSet

case class JsonValidateComparator(comparator: Comparator[JsonNode] = new Comparator(), keys: Seq[String] = Nil)
  extends ComparatorWithKeys[JsonNode]

case class JsonValidator(expected: Iterator[JsonNode], actual: Iterator[JsonNode])
  extends Validator[JsonNode, JsonNode](expected, actual, JsonValidateComparator()) {
  protected def asPathRowSource(i: Iterator[JsonNode]) = JsonPathRowSource(i, keysAsCols).asIndexedRepresentationIterator()
  protected val fromSeqString = (r: Seq[String]) =>
    new IndexedRepresentation[JsonNode] {
      val indexed = r.tail
      val colDefs = ColumnDefinition.fromColumnNames(keys)
      val representation: JsonNode = r(0)
      def prettyPrint = representation.toString
    }
  protected val toSeqString = (r: IndexedRepresentation[JsonNode]) => r.representation.toString() :: r.indexed.toList
  val self = new IndexedDiffSet[JsonNode, JsonNode] {
    val Seq(left, right) = for (i <- Seq(expected, actual)) yield sort(asPathRowSource(i), fromSeqString, toSeqString)
    def inflate(r: JsonNode) = Some(tree.JsonNode(r))
    def inflateLeft(r: JsonNode) = inflate(r)
    def inflateRight(r: JsonNode) = inflate(r)
    def comparator = validator.comparator
  }
}/*
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
