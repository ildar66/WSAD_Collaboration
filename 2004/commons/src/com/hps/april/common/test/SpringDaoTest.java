package com.hps.april.common.test;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class SpringDaoTest extends AbstractSpringContextOpenSessionTest {

	protected static String TRANSACTION_MANAGER_NAME = "april.transactionManager";
	protected PlatformTransactionManager txManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		txManager  = (PlatformTransactionManager) getBean(TRANSACTION_MANAGER_NAME);
	}

	protected void runTest() throws Throwable {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = txManager.getTransaction(def);

		try {
			super.runTest();
		} catch (Throwable tx){
			txManager.rollback(status);	
			throw tx;
		}
		
		txManager.commit(status);
	}
	
	protected void tearDown() throws Exception {
		txManager = null;
		super.tearDown();
	}
	
}
