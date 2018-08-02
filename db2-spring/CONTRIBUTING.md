# Contributing

## Issues

Please [read these guidelines](http://ibm.biz/cdt-issue-guide) before opening an issue.
If you still need to open an issue then we ask that you complete the template as
fully as possible.

## Pull requests

We welcome pull requests, but ask contributors to keep in mind the following:

* Only PRs with the template completed will be accepted
* We will not accept PRs for user specific functionality

### Developer Certificate of Origin

In order for us to accept pull-requests, the contributor must sign-off a
[Developer Certificate of Origin (DCO)](DCO1.1.txt). This clarifies the
intellectual property license granted with any contribution. It is for your
protection as a Contributor as well as the protection of IBM and its customers;
it does not change your rights to use your own Contributions for any other purpose.

Please read the agreement and acknowledge it by ticking the appropriate box in the PR
 text, for example:

- [x] Tick to sign-off your agreement to the Developer Certificate of Origin (DCO) 1.1

## General information

Db2-spring is written in Java and uses Gradle as its build tool.

### Projects
There are two sub-projects in db2-spring each of which produces an artifact for publishing.

#### db2-spring-boot-starter
The db2-spring-boot-starter jar provides a custom [Db2 autoconfiguration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-developing-auto-configuration.html) for `com.ibm.db2.jcc.DB2XADataSource` and `com.ibm.db2.jcc.DB2SimpleDataSource` beans.

#### db2-spring-framework
The db2-spring-framework jar provides a custom annotation called `com.ibm.db2.spring.framework.EnableDb2` which when applied provides  `com.ibm.db2.jcc.DB2XADataSource` and `com.ibm.db2.jcc.DB2SimpleDataSource` beans.

## Requirements

- gradle
- Java 1.8

### Installing requirements

#### Java

Follow the instructions for your platform.

#### Gradle

The project uses the gradle wrapper to download the specified version of gradle.
The gradle wrapper is run by using the following command:

```bash
$ ./gradlew
```
Note: on windows the command to run is gradlew.bat rather than gradlew

## Building

The project should build out of the box with:

```bash
$ ./gradlew build
```

## Testing

### Running the tests

The tests run using [Mockito](http://site.mockito.org/) to mock the Db2 client:

```bash
$ ./gradlew test
```
