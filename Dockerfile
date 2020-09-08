FROM openjdk:11
ADD target/airlineSchedularCC.jar airlineSchedularCC.jar
EXPOSE 8036 9093
ENTRYPOINT ["java","-jar","airlineSchedularCC.jar"]