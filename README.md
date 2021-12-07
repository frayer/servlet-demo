# servlet-demo

A simple demonstration of traditional, Servlet based application development, without the use of additional frameworks.

## Setup

This project has been built and tested against the following versions of software:

[Apache Maven 3.x](https://maven.apache.org/install.html)
[Apache Tomcat 9.x on Java 11](https://tomcat.apache.org/download-90.cgi)

## Overview

This is a very simple JEE Servlet application, showing the foundations of web based development on Java platforms.  Individual files within this project are commented to show how they relate to one another when serving up the application.

> pom.xml

POM files describe to Maven (a Java build tool) the makeup and dependencies of your application.  Maven reads this descriptor in order to build and package your application in a consistent way.

> src/main/webapp/web.xml

In older versions of the Servlet specification, this file would be filled with configuration describing your Servlets, how they're accessed, and optionally JNDI configuration to obtain database connections or other information.  This one only serves to tell our Servlet container (Tomcat) that we're running on Servlet spec 3.0.

> src/main/webapp/WEB-INF/jsp/home.jsp

Placing JSP's (an HTML templating engine) in the `WEB-INF/jsp` directory is a convention to hide them from public view, and only allow them to be accessed by requesting a Servlet endpoint first.  This is common when the JSP cannot otherwise be rendered without first running business logic in a Servlet first.  For example, retrieving information from a database, before rendering the data in the JSP template.

> src/main/java/***

The Java code for the Servlet and a simple Model class for this application are contained here.  The convention of placing these in `src/main/java` is important, as this is the default location Maven looks to compile any Java code.  This is "convention over configuration", and is common in other language build tools as well.


## Build

From the root of this project, execute a Maven package

```bash
mvn clean package
```

This will produce the file `./target/servlet-demo.war`

Inspect the contents of the file using the `jar` command and compare the directory structure against the structure of this source code repository.  JEE applications require a specific structure, and Maven along with the `maven-war-plugin` have taken care of this for you.  As long as you stick to certain naming and directory conventions, Maven will save you a lot of time during build and package phases.

```bash
jar -tf ./target/servlet-demo.war
```

## Deploy

With Apache Tomcat running, copy the `servlet-demo.war` file into the `webapps` directory in your Tomcat installation folder.  For example, if Tomcat was installed in `/opt/apache-tomcat`, copy the WAR file to `/opt/apache-tomcat/webapps`.  Watch the log files of Tomcat, and in a few seconds you'll see logs indicating it saw the new WAR file and deployed it.

Without any additional configuration, the application will be available on the context root `/servlet-demo/` of your Tomcat server.  If running locally, this would be:

http://localhost:8080/servlet-demo/

On a remote server, replace `localhost` with the IP address of the server:

http://<ip-address>:8080/servlet-demo/

## Re-deploy

If you change the code, you can repeat the Build and Deploy steps above.  After a few seconds, Tomcat will see the new version of your application and re-deploy it.
