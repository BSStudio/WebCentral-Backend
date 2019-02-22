# BSS-WC

This is the backend of our university studio's central web application.

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Git
* JDK (10)
* Apache Maven (3.5.X)
* PostgreSQL (11.X)
* Docker
* IntelliJ Idea (ultimate)
  * CheckStyle-IDEA plugin
  * PMDPlugin plugin
  * FindBugs-IDEA plugin
* Postman

### Database

Install PostgreSQL and set its port to the default `5432`, create database with the name `webcentral`.

### Checkstyle configuration

Intsall Checkstyle plugin in the IDE and at `File > Setting > Editor > Code Style > Java`,
next to `Scheme` from the `Settings` drop down menu choose `Import Scheme` and click on
`Checkstyle Configuration`. In the pop-up window browse for the directory of
the repository and in the `build > rules` folder choose the `checkstyle_rules.xml`.

## Building

Run maven command in the project's root directory:

```
mvn clean install
```

The goals that are proceeded during the build:
* Static code analyzing
* Compilation
* Unit tests run
* Code coverage check
* Mutation testing
* Integration tests run
* Jar creation

## Running

You can run the application from the IDE or from command line. If you do it locally activate the `dev` profile as a VM option. 

```
java -jar bss-webcentral-backend.jar -Dspring.profiles.active=dev
```

## Deployment

```
docker run -p 8080:8080 -e JAVA_JVM_ARGS="-Dspring.profiles.active=staging" image
```

## Authors

* Péter Veress @veresspeter
* Bence Csik @csikb
* Boldizsár Márta @mboldi
* Bálint Király @kiralybalint

![BSS logo](https://bsstudio.hu/files/site_content/illustration/bss_logo_a.png)
