FROM kdvolder/mvn-plus-npm

COPY . src/.

RUN mvn -f src/pom.xml -U clean install

EXPOSE 8081

CMD ["sh","-c", "mvn -f src/people-war/pom.xml tomcat7:run-war-only"]

