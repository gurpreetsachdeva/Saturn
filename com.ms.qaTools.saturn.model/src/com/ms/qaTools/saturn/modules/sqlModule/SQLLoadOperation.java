package com.ms.qaTools.saturn.modules.sqlModule;

import org.eclipse.emf.common.util.EList;

import com.ms.qaTools.saturn.types.ResourceDefinition;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>SQL Load Operation</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getInputResource <em>Input Resource</em>}</li>
 * <li>{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getTables <em>Tables</em>}</li>
 * <li>{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getTableGroupDefinition <em>Table Group
 * Definition</em>}</li>
 * <li>{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getDefaultInputResourceType <em>Default Input
 * Resource Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see com.ms.qaTools.saturn.modules.sqlModule.SqlModulePackage#getSQLLoadOperation()
 * @model annotation="http://www.ms.com/2006/pdsttools/xsd2perl executable='Saturn::Module::SQL::Load::Executable'"
 *        annotation="http://www.ms.com/2011/pdsttools/xsd2scriptLib createUpdater='true' name='sqlLoad'"
 *        extendedMetaData="name='Load' kind='elementOnly'"
 * @generated
 */
public interface SQLLoadOperation extends AbstractSQLOperation
{
  /**
   * Returns the value of the '<em><b>Input Resource</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Input Resource</em>' containment reference isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input Resource</em>' containment reference.
   * @see #setInputResource(ResourceDefinition)
   * @see com.ms.qaTools.saturn.modules.sqlModule.SqlModulePackage#getSQLLoadOperation_InputResource()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='InputResource' namespace='##targetNamespace'"
   * @generated
   */
  ResourceDefinition getInputResource();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getInputResource <em>Input Resource</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Input Resource</em>' containment reference.
   * @see #getInputResource()
   * @generated
   */
  void setInputResource(ResourceDefinition value);

  /**
   * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
   * The list contents are of type {@link com.ms.qaTools.saturn.modules.sqlModule.LoadTable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tables</em>' containment reference list isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tables</em>' containment reference list.
   * @see com.ms.qaTools.saturn.modules.sqlModule.SqlModulePackage#getSQLLoadOperation_Tables()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='Tables' namespace='##targetNamespace'"
   * @generated
   */
  EList<LoadTable> getTables();

  /**
   * Returns the value of the '<em><b>Table Group Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Table Group Definition</em>' containment reference isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Table Group Definition</em>' containment reference.
   * @see #setTableGroupDefinition(ResourceDefinition)
   * @see com.ms.qaTools.saturn.modules.sqlModule.SqlModulePackage#getSQLLoadOperation_TableGroupDefinition()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='TableGroupDefinition' namespace='##targetNamespace'"
   * @generated
   */
  ResourceDefinition getTableGroupDefinition();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getTableGroupDefinition <em>Table Group Definition</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Table Group Definition</em>' containment reference.
   * @see #getTableGroupDefinition()
   * @generated
   */
  void setTableGroupDefinition(ResourceDefinition value);

  /**
   * Returns the value of the '<em><b>Default Input Resource Type</b></em>' attribute.
   * The default value is <code>"CSV"</code>.
   * The literals are from the enumeration {@link com.ms.qaTools.saturn.modules.sqlModule.IOFileTypesEnum}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Input Resource Type</em>' attribute isn't clear, there really should be more of
   * a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Input Resource Type</em>' attribute.
   * @see com.ms.qaTools.saturn.modules.sqlModule.IOFileTypesEnum
   * @see #isSetDefaultInputResourceType()
   * @see #unsetDefaultInputResourceType()
   * @see #setDefaultInputResourceType(IOFileTypesEnum)
   * @see com.ms.qaTools.saturn.modules.sqlModule.SqlModulePackage#getSQLLoadOperation_DefaultInputResourceType()
   * @model default="CSV" unsettable="true"
   *        extendedMetaData="kind='attribute' name='defaultInputResourceType'"
   * @generated
   */
  IOFileTypesEnum getDefaultInputResourceType();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getDefaultInputResourceType <em>Default Input Resource Type</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Default Input Resource Type</em>' attribute.
   * @see com.ms.qaTools.saturn.modules.sqlModule.IOFileTypesEnum
   * @see #isSetDefaultInputResourceType()
   * @see #unsetDefaultInputResourceType()
   * @see #getDefaultInputResourceType()
   * @generated
   */
  void setDefaultInputResourceType(IOFileTypesEnum value);

  /**
   * Unsets the value of the '{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getDefaultInputResourceType <em>Default Input Resource Type</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #isSetDefaultInputResourceType()
   * @see #getDefaultInputResourceType()
   * @see #setDefaultInputResourceType(IOFileTypesEnum)
   * @generated
   */
  void unsetDefaultInputResourceType();

  /**
   * Returns whether the value of the '{@link com.ms.qaTools.saturn.modules.sqlModule.SQLLoadOperation#getDefaultInputResourceType <em>Default Input Resource Type</em>}' attribute is set.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return whether the value of the '<em>Default Input Resource Type</em>' attribute is set.
   * @see #unsetDefaultInputResourceType()
   * @see #getDefaultInputResourceType()
   * @see #setDefaultInputResourceType(IOFileTypesEnum)
   * @generated
   */
  boolean isSetDefaultInputResourceType();

} // SQLLoadOperation
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
