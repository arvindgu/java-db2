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
package com.ibm.db2.spring.boot;


import javax.sql.DataSource;
import javax.sql.XADataSource;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ibm.db2.jcc.DB2SimpleDataSource;
import com.ibm.db2.jcc.DB2XADataSource;

@Configuration
@EnableConfigurationProperties(Db2ConfigurationProperties.class)
public class Db2AutoConfiguration {
	
	@Autowired
    private Db2ConfigurationProperties config;
	
    
    @Bean
    @ConfigurationProperties(prefix = "db2")
    @ConditionalOnMissingBean({ DB2XADataSource.class})
    public XADataSource getXADataSource() throws ClassNotFoundException{
    	loadDb2DriverJar();
    	DB2XADataSource xaDataSource = new DB2XADataSource();  
    	setProperties(xaDataSource);
    	return xaDataSource;
    }
    
    
    @Bean
    @ConfigurationProperties(prefix = "db2")
    @ConditionalOnMissingBean({ DB2SimpleDataSource.class})
    public DataSource getDB2SimpleDataSource() throws ClassNotFoundException{
    	loadDb2DriverJar();
    	DataSource dataSource =  new DB2SimpleDataSource();
    	setProperties(dataSource);
    	return dataSource;
    }
	
    
    private void setProperties(Object ds){
    	
    	PropertyAccessor propertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(ds);
    	
    	propertyAccessor.setPropertyValue("driverType", new Integer(4));
    	
    	if(config.getServerName() != null ){
    		propertyAccessor.setPropertyValue("serverName", config.getServerName());
    	}
    	
    	if(config.getPortNumber() != null ){
    		try{
    			propertyAccessor.setPropertyValue("portNumber", new Integer(Integer.parseInt(config.getPortNumber())));
    		}catch(NumberFormatException e){}
    	}
    	
    	if(config.getDatabaseName() != null ){
    		propertyAccessor.setPropertyValue("databaseName", config.getDatabaseName());
    	}
    	
    	if(config.getUser() != null ){
    		propertyAccessor.setPropertyValue("user", config.getUser());
    	}
    	
    	if(config.getPassword() != null ){
    		propertyAccessor.setPropertyValue("password", config.getPassword());
    	}
    	
    }
    

	//Load the DB2 Jcc Driver
	private void loadDb2DriverJar() throws ClassNotFoundException {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
	}

}
