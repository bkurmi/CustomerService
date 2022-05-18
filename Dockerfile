FROM java:8
ADD build/libs/CustomerService-0.0.1-SNAPSHOT.jar webapp.jar
ENTRYPOINT ["java", "-jar", "webapp.jar"]
EXPOSE 8092