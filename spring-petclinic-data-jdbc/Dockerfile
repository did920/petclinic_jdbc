FROM eclipse-temurin:17.0.7_7-jdk-centos7

RUN mkdir -p /petclinic \
    && ln -s /petclinic/properties/application.properties /petclinic/petclinic.properties 

WORKDIR /petclinic
COPY build/libs/spring-petclinic-data-jdbc-3.0.0.BUILD-SNAPSHOT.jar petclinic.jar
EXPOSE 8080

ENTRYPOINT ["bash" ,"-c", "java ${JAVA_OPTION} -jar petclinic.jar --spring.config.location=classpath:/application.properties,petclinic.properties"]
