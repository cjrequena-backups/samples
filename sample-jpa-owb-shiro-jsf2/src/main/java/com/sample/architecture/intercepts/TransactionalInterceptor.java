/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.sample.architecture.intercepts;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.sample.architecture.bindings.MyEntityManager;
import com.sample.architecture.bindings.Transactional;



@Transactional
@Interceptor
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@MyEntityManager
	transient EntityManager entityManager;

	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			if (!transaction.isActive()) {
				transaction.begin();
			}
			return context.proceed();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		} finally {
			if (transaction != null && transaction.isActive()) {
				try {
					transaction.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// @Resource
	// private UserTransaction utx;
	//
	// @AroundInvoke
	// public Object openIfNoTransaction(InvocationContext ic) throws Exception
	// {
	// boolean startedTransaction = false;
	// if (this.utx.getStatus() != Status.STATUS_ACTIVE) {
	// this.utx.begin();
	// startedTransaction = true;
	// }
	// Object ret = null;
	// try {
	// ret = ic.proceed();
	// if (startedTransaction){
	// this.utx.commit();
	// }
	// } catch (Exception e) {
	// if (startedTransaction)
	// try {
	// this.utx.rollback();
	// } catch (Exception e1) {
	// //e.printStackTrace();
	// }
	// throw e;
	// }
	// return ret;
	// }
}
