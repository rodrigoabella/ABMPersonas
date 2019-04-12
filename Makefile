SHELL := /bin/bash

tomcat-local:
	mvn -f ./people-war/pom.xml tomcat7:run-war
