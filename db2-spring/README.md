# Db2 Spring Library

The is a library to provide Spring developers easy configuration of the [official Db2 library for Java](https://github.com/ibmdb/java-db2/).

The library is split into two parts:
* `db2-spring-boot-starter` for [Spring Boot](https://projects.spring.io/spring-boot/) applications

* `db2-spring-framework` for [Spring Framework](https://projects.spring.io/spring-framework/) applications


* [Installation and Usage](#installation-and-usage)
* [Getting Started](#getting-started)
* [Related Documentation](#related-documentation)
* [Development](#development)
    * [Contributing](CONTRIBUTING.md)
    * [Test Suite](CONTRIBUTING.md#running-the-tests)
    * [Using in Other Projects](#using-in-other-projects)
    * [License](#license)
    * [Issues](#issues)

## Installation and Usage

### Spring Boot Applications

Gradle:
```groovy
dependencies {
    compile group: 'com.db2', name: 'db2-spring-boot-starter', version: '0.0.1'
}
```

Maven:
~~~ xml
<dependency>
  <groupId>com.db2</groupId>
  <artifactId>db2-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
~~~

### Spring Framework Applications

Gradle:
```groovy
dependencies {
    compile group: 'com.db2', name: 'db2-spring-framework', version: '0.0.1'
}
```

Maven:
~~~ xml
<dependency>
  <groupId>com.db2</groupId>
  <artifactId>db2-spring-framework</artifactId>
  <version>0.0.1</version>
</dependency>
~~~

## Getting Started

This section contains simple examples of connecting to Db2 using the two libraries.

### Spring Boot Applications

To enable auto-configuration you must provide the following properties to define the connection to your Db2 instance:

* `db2.serverName`
* `db2.portNumber`
* `db2.user`
* `db2.password`
* `db2.databaseName`

For example in an `application.properties` file:

~~~
db2.serverName=myServer
db2.portNumber=myPortNumber
db2.user: myUsername
db2.password: myPassword
db2.databaseName=myDatabase
~~~

Spring Boot will create `com.ibm.db2.jcc.DB2XADataSource` and `com.ibm.db2.jcc.DB2SimpleDataSource` beans that can be used to interact with your Db2 instance:

`With DB2XADataSource`
~~~ java
@Autowired
private XADataSource xaDataSource;

public XAConnection getXAConnection() {
    return xaDataSource.getXAConnection();
}
~~~

`With DB2SimpleDataSource`

~~~ java
@Autowired
private DataSource dataSource;

public Connection getConnection() {
    return dataSource.getConnection();
}
~~~



### Spring Framework Applications

You must provide the following properties to define the connection to your Db2 instance:

* `db2.serverName`
* `db2.portNumber`
* `db2.user`
* `db2.password`
* `db2.databaseName`

To enable the creation of the `com.ibm.db2.jcc.DB2XADataSource` bean you must add an `com.ibm.db2.spring.framework.EnableDb2` annotation to your application configuration:


`With DB2XADataSource`

~~~ java
@Configuration
@EnableWebMvc
@EnableDb2
@ComponentScan
public class SpringConfig {}
~~~

~~~ java
@Autowired
private XADataSource xaDataSource;

public XAConnection getXAConnection() {
    return xaDataSource.getXAConnection();
}
~~~


`With DB2SimpleDataSource`

~~~ java
@Configuration
@EnableWebMvc
@EnableDb2
@ComponentScan
public class SpringConfig {}
~~~

~~~ java
@Autowired
private DataSource dataSource;

public Connection getConnection() {
    return dataSource.getConnection();
}
~~~


## Related documentation
* [Spring Boot documentation](https://projects.spring.io/spring-boot/)
* [Spring Framework documentation](https://projects.spring.io/spring-framework/)

# Development

For information about contributing, building, and running tests see the [CONTRIBUTING.md](CONTRIBUTING.md).

### Using in Other Projects

The preferred approach for using db2-spring in other projects is to use the Gradle or Maven dependency as described above.

### License

Copyright © 2018 IBM Corp. All rights reserved.

Licensed under the apache license, version 2.0 (the "license"); you may not use this file except in compliance with the license.  you may obtain a copy of the license at

    http://www.apache.org/licenses/LICENSE-2.0.html

Unless required by applicable law or agreed to in writing, software distributed under the license is distributed on an "as is" basis, without warranties or conditions of any kind, either express or implied. See the license for the specific language governing permissions and limitations under the license.

### Issues

Before opening a new issue please consider the following:
* Please try to reproduce the issue using the latest version.
* Please check the [existing issues](https://github.com/db2/db2-spring/issues)
to see if the problem has already been reported. Note that the default search
includes only open issues, but it may already have been closed.
* When opening a new issue [here in github](../../issues) please complete the template fully.
