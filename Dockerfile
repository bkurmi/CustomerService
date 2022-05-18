FROM java:8
EXPOSE 8092
ARG JAR_FILE=target/customer-details-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} customer-details-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","customer-details-0.0.1-SNAPSHOT.jar"]