package com.ms.qaTools.saturn.modules.sqlModule.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.ms.qaTools.saturn.modules.sqlModule.ExecuteCommand;
import com.ms.qaTools.saturn.modules.sqlModule.SQLExecuteOperation;
import com.ms.qaTools.saturn.modules.sqlModule.SqlModulePackage;
import com.ms.qaTools.saturn.types.ResourceDefinition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>SQL Execute Operation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.ms.qaTools.saturn.modules.sqlModule.impl.SQLExecuteOperationImpl#getParameterResource <em>Parameter Resource</em>}</li>
 *   <li>{@link com.ms.qaTools.saturn.modules.sqlModule.impl.SQLExecuteOperationImpl#getCommands <em>Commands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SQLExecuteOperationImpl extends AbstractSQLOperationImpl implements SQLExecuteOperation
{
  /**
   * The cached value of the '{@link #getParameterResource() <em>Parameter Resource</em>}' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getParameterResource()
   * @generated
   * @ordered
   */
  protected ResourceDefinition    parameterResource;

  /**
   * The cached value of the '{@link #getCommands() <em>Commands</em>}' containment reference list.
   * <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * @see #getCommands()
   * @generated
   * @ordered
   */
  protected EList<ExecuteCommand> commands;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected SQLExecuteOperationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SqlModulePackage.Literals.SQL_EXECUTE_OPERATION;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public ResourceDefinition getParameterResource()
  {
    return parameterResource;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameterResource(ResourceDefinition newParameterResource, NotificationChain msgs)
  {
    ResourceDefinition oldParameterResource = parameterResource;
    parameterResource = newParameterResource;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE, oldParameterResource, newParameterResource);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setParameterResource(ResourceDefinition newParameterResource)
  {
    if (newParameterResource != parameterResource)
    {
      NotificationChain msgs = null;
      if (parameterResource != null)
        msgs = ((InternalEObject)parameterResource).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE, null, msgs);
      if (newParameterResource != null)
        msgs = ((InternalEObject)newParameterResource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE, null, msgs);
      msgs = basicSetParameterResource(newParameterResource, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE, newParameterResource, newParameterResource));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public EList<ExecuteCommand> getCommands()
  {
    if (commands == null)
    {
      commands = new EObjectContainmentEList<ExecuteCommand>(ExecuteCommand.class, this, SqlModulePackage.SQL_EXECUTE_OPERATION__COMMANDS);
    }
    return commands;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE:
        return basicSetParameterResource(null, msgs);
      case SqlModulePackage.SQL_EXECUTE_OPERATION__COMMANDS:
        return ((InternalEList<?>)getCommands()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE:
        return getParameterResource();
      case SqlModulePackage.SQL_EXECUTE_OPERATION__COMMANDS:
        return getCommands();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE:
        setParameterResource((ResourceDefinition)newValue);
        return;
      case SqlModulePackage.SQL_EXECUTE_OPERATION__COMMANDS:
        getCommands().clear();
        getCommands().addAll((Collection<? extends ExecuteCommand>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE:
        setParameterResource((ResourceDefinition)null);
        return;
      case SqlModulePackage.SQL_EXECUTE_OPERATION__COMMANDS:
        getCommands().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SqlModulePackage.SQL_EXECUTE_OPERATION__PARAMETER_RESOURCE:
        return parameterResource != null;
      case SqlModulePackage.SQL_EXECUTE_OPERATION__COMMANDS:
        return commands != null && !commands.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} // SQLExecuteOperationImpl
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
