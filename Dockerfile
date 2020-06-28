FROM openjdk:11
ADD target/airlineSchedular.jar airlineSchedular.jar
EXPOSE 8036
ENTRYPOINT ["java","-jar","airlineSchedular.jar"]