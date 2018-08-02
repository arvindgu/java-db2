/*
 * Copyright © 2018 IBM Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.ibm.db2.spring.framework.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.db2.jcc.DB2SimpleDataSource;
import com.ibm.db2.spring.framework.EnableDb2;


public class EnableDb2TestSimpleDataSource {

	
    private AnnotationConfigApplicationContext context;
    
    private static DataSource mockDB2SimpleDataSource = mock(DB2SimpleDataSource.class);
    
    @Before
    public void setUp() {
        this.context = new AnnotationConfigApplicationContext();
    }

    @After
    public void cleanup() {
        if (this.context != null) {
            this.context.close();
        }
    }

    
    
    @EnableDb2
    @Configuration
    protected static class MockDb2DataSourceConfig {
        @Bean
        public DataSource  getDataSource() {
            return mockDB2SimpleDataSource;
        }
    }
    
    
    @Test
    public void dataSourceCreation() {
    	this.context.register(MockDb2DataSourceConfig.class);
    	EnvironmentTestUtils.addEnvironment(this.context, "db2.serverName=localhost");
    	EnvironmentTestUtils.addEnvironment(this.context, "db2.portNumber=50000");
    	EnvironmentTestUtils.addEnvironment(this.context, "db2.databaseName=sample");
    	EnvironmentTestUtils.addEnvironment(this.context, "db2.user=<db2_user>");
    	EnvironmentTestUtils.addEnvironment(this.context, "db2.password=<db2_password>");
    	EnvironmentTestUtils.addEnvironment(this.context, "db2.globalPropertyFile=<Absolute_Path_Of_Jcc_Global_Properties_File>");
    	this.context.refresh();
    	
    	DataSource dataSource = this.context.getBean(DB2SimpleDataSource.class);
        assertNotNull(dataSource);
    }

}
