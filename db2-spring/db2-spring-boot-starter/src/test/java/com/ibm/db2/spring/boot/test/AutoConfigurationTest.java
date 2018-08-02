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

package com.ibm.db2.spring.boot.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutoConfigurationTest implements CommandLineRunner {

	@Autowired
    private XADataSource xaDataSource;
	
	@Autowired
	private DataSource dataSource;
	
	public static void main(String[] args) {
		SpringApplication.run(AutoConfigurationTest.class, args);
	}
    
    
	@Override
    public void run(String... args) throws Exception{
		try{
			testDB2XADaSource();
			testDB2SimpleDataSource();
		}
        catch(Exception e){
        	e.printStackTrace();
        }
    }
	
	private void testDB2XADaSource() throws SQLException{
		assertNotNull(xaDataSource);
		XAConnection con = xaDataSource.getXAConnection();
        assertNotNull(con);
        if(con != null){
        	con.close();
        }
        
	}
	
	private void testDB2SimpleDataSource() throws SQLException{
		assertNotNull(dataSource);
		Connection con = dataSource.getConnection();
        assertNotNull(con);
        if(con != null){
        	con.close();
        }
	}
    
}
