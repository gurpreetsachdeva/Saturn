package com.ms.qaTools.saturn.modules.soapIOModule;

import java.math.BigInteger;

import com.ms.qaTools.saturn.types.ResourceDefinition;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Soap IO Get Operation</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getOutput <em>Output</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getTerminationCondition <em>Termination Condition</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#isExtractBody <em>Extract Body</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getMaxRetries <em>Max Retries</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage#getSoapIOGetOperation()
 * @model extendedMetaData="name='SoapIOGetOperation' kind='elementOnly'"
 * @generated
 */
public interface SoapIOGetOperation extends AbstractSoapIOOperation
{
  /**
   * Returns the value of the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' containment reference isn't clear, there really should be more of a
   * description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output</em>' containment reference.
   * @see #setOutput(ResourceDefinition)
   * @see com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage#getSoapIOGetOperation_Output()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='Output' namespace='##targetNamespace'"
   * @generated
   */
  ResourceDefinition getOutput();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getOutput <em>Output</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Output</em>' containment reference.
   * @see #getOutput()
   * @generated
   */
  void setOutput(ResourceDefinition value);

  /**
   * Returns the value of the '<em><b>Termination Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Termination Condition</em>' containment reference isn't clear, there really should be
   * more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Termination Condition</em>' containment reference.
   * @see #setTerminationCondition(AbstractTerminationCondition)
   * @see com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage#getSoapIOGetOperation_TerminationCondition()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='TerminationCondition' namespace='##targetNamespace'"
   * @generated
   */
  AbstractTerminationCondition getTerminationCondition();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getTerminationCondition <em>Termination Condition</em>}' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Termination Condition</em>' containment reference.
   * @see #getTerminationCondition()
   * @generated
   */
  void setTerminationCondition(AbstractTerminationCondition value);

  /**
   * Returns the value of the '<em><b>Extract Body</b></em>' attribute. The default value is <code>"false"</code>. <!--
   * begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Extract Body</em>' attribute isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Extract Body</em>' attribute.
   * @see #isSetExtractBody()
   * @see #unsetExtractBody()
   * @see #setExtractBody(boolean)
   * @see com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage#getSoapIOGetOperation_ExtractBody()
   * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
   *        extendedMetaData="kind='attribute' name='extractBody'"
   * @generated
   */
  boolean isExtractBody();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#isExtractBody <em>Extract Body</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Extract Body</em>' attribute.
   * @see #isSetExtractBody()
   * @see #unsetExtractBody()
   * @see #isExtractBody()
   * @generated
   */
  void setExtractBody(boolean value);

  /**
   * Unsets the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#isExtractBody <em>Extract Body</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #isSetExtractBody()
   * @see #isExtractBody()
   * @see #setExtractBody(boolean)
   * @generated
   */
  void unsetExtractBody();

  /**
   * Returns whether the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#isExtractBody <em>Extract Body</em>}' attribute is set.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return whether the value of the '<em>Extract Body</em>' attribute is set.
   * @see #unsetExtractBody()
   * @see #isExtractBody()
   * @see #setExtractBody(boolean)
   * @generated
   */
  boolean isSetExtractBody();

  /**
   * Returns the value of the '<em><b>Max Retries</b></em>' attribute. The default value is <code>"1"</code>. <!--
   * begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Max Retries</em>' attribute isn't clear, there really should be more of a description
   * here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>Max Retries</em>' attribute.
   * @see #isSetMaxRetries()
   * @see #unsetMaxRetries()
   * @see #setMaxRetries(BigInteger)
   * @see com.ms.qaTools.saturn.modules.soapIOModule.SoapIOModulePackage#getSoapIOGetOperation_MaxRetries()
   * @model default="1" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Integer"
   *        extendedMetaData="kind='attribute' name='maxRetries'"
   * @generated
   */
  BigInteger getMaxRetries();

  /**
   * Sets the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getMaxRetries <em>Max Retries</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @param value the new value of the '<em>Max Retries</em>' attribute.
   * @see #isSetMaxRetries()
   * @see #unsetMaxRetries()
   * @see #getMaxRetries()
   * @generated
   */
  void setMaxRetries(BigInteger value);

  /**
   * Unsets the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getMaxRetries <em>Max Retries</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @see #isSetMaxRetries()
   * @see #getMaxRetries()
   * @see #setMaxRetries(BigInteger)
   * @generated
   */
  void unsetMaxRetries();

  /**
   * Returns whether the value of the '{@link com.ms.qaTools.saturn.modules.soapIOModule.SoapIOGetOperation#getMaxRetries <em>Max Retries</em>}' attribute is set.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @return whether the value of the '<em>Max Retries</em>' attribute is set.
   * @see #unsetMaxRetries()
   * @see #getMaxRetries()
   * @see #setMaxRetries(BigInteger)
   * @generated
   */
  boolean isSetMaxRetries();

} // SoapIOGetOperation
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
