package com.ms.qaTools.xml

import org.w3c.dom.{Document,Node}

import com.ms.qaTools.io.rowSource.ColumnDefinitions
import com.ms.qaTools.io.rowSource.file.XPathRowSource
import com.ms.qaTools.io.rowSource.file.XmlRowSource
import com.ms.qaTools.tree.TreeNode
import com.ms.qaTools.tree.extraction.DetachStrategy

case class XmlExtractor(xmlRowSource: Iterator[Document],
                        xPaths: Seq[(String, String)],
                        namespaces: Map[String, String] = Map(),
                        detachNodes: DetachStrategy[TreeNode[Node]] = DetachStrategy.default)
extends Iterator[Seq[String]] with ColumnDefinitions {
  protected val nsContext = xmlRowSource match {
    case i: XmlRowSource => i.namespaceContext + namespaces
    case _ => NamespaceContextImpl(namespaces)
  }
  protected val xPathRowSource = XPathRowSource(xPaths, xmlRowSource, detachNodes)(nsContext)

  def colDefs = xPathRowSource.colDefs
  def next = xPathRowSource.next.map { _._1 }
  def hasNext = xPathRowSource.hasNext
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
