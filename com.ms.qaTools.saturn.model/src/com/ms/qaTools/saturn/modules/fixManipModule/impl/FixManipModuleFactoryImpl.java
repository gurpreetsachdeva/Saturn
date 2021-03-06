package com.ms.qaTools.saturn.modules.fixManipModule.impl;

import com.ms.qaTools.saturn.modules.fixManipModule.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import com.ms.qaTools.saturn.modules.fixManipModule.AddOperation;
import com.ms.qaTools.saturn.modules.fixManipModule.DeleteOperation;
import com.ms.qaTools.saturn.modules.fixManipModule.DocumentRoot;
import com.ms.qaTools.saturn.modules.fixManipModule.FixManipConfiguration;
import com.ms.qaTools.saturn.modules.fixManipModule.FixManipModuleFactory;
import com.ms.qaTools.saturn.modules.fixManipModule.FixManipModulePackage;
import com.ms.qaTools.saturn.modules.fixManipModule.ReplaceOperation;
import com.ms.qaTools.saturn.modules.fixManipModule.UpdateBodyLengthOperation;
import com.ms.qaTools.saturn.modules.fixManipModule.UpdateCheckSumOperation;
import com.ms.qaTools.saturn.modules.fixManipModule.ValueReplaceOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * @generated
 */
public class FixManipModuleFactoryImpl extends EFactoryImpl implements FixManipModuleFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public static FixManipModuleFactory init()
  {
    try
    {
      FixManipModuleFactory theFixManipModuleFactory = (FixManipModuleFactory)EPackage.Registry.INSTANCE.getEFactory(FixManipModulePackage.eNS_URI);
      if (theFixManipModuleFactory != null)
      {
        return theFixManipModuleFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FixManipModuleFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public FixManipModuleFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case FixManipModulePackage.ADD_OPERATION: return createAddOperation();
      case FixManipModulePackage.DELETE_OPERATION: return createDeleteOperation();
      case FixManipModulePackage.DOCUMENT_ROOT: return createDocumentRoot();
      case FixManipModulePackage.FIX_MANIP_CONFIGURATION: return createFixManipConfiguration();
      case FixManipModulePackage.REPLACE_OPERATION: return createReplaceOperation();
      case FixManipModulePackage.UPDATE_BODY_LENGTH_OPERATION: return createUpdateBodyLengthOperation();
      case FixManipModulePackage.UPDATE_CHECK_SUM_OPERATION: return createUpdateCheckSumOperation();
      case FixManipModulePackage.VALUE_REPLACE_OPERATION: return createValueReplaceOperation();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public AddOperation createAddOperation()
  {
    AddOperationImpl addOperation = new AddOperationImpl();
    return addOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public DeleteOperation createDeleteOperation()
  {
    DeleteOperationImpl deleteOperation = new DeleteOperationImpl();
    return deleteOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public DocumentRoot createDocumentRoot()
  {
    DocumentRootImpl documentRoot = new DocumentRootImpl();
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public FixManipConfiguration createFixManipConfiguration()
  {
    FixManipConfigurationImpl fixManipConfiguration = new FixManipConfigurationImpl();
    return fixManipConfiguration;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public ReplaceOperation createReplaceOperation()
  {
    ReplaceOperationImpl replaceOperation = new ReplaceOperationImpl();
    return replaceOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public UpdateBodyLengthOperation createUpdateBodyLengthOperation()
  {
    UpdateBodyLengthOperationImpl updateBodyLengthOperation = new UpdateBodyLengthOperationImpl();
    return updateBodyLengthOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public UpdateCheckSumOperation createUpdateCheckSumOperation()
  {
    UpdateCheckSumOperationImpl updateCheckSumOperation = new UpdateCheckSumOperationImpl();
    return updateCheckSumOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public ValueReplaceOperation createValueReplaceOperation()
  {
    ValueReplaceOperationImpl valueReplaceOperation = new ValueReplaceOperationImpl();
    return valueReplaceOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public FixManipModulePackage getFixManipModulePackage()
  {
    return (FixManipModulePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FixManipModulePackage getPackage()
  {
    return FixManipModulePackage.eINSTANCE;
  }

} // FixManipModuleFactoryImpl
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
