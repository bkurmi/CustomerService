FROM java:8
EXPOSE 8092
ARG JAR_FILE=build/libs/CustomerService-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} CustomerService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","CustomerService-0.0.1-SNAPSHOT.jar"]