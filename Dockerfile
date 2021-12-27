FROM openjdk:11
ADD target/coursereg.jar coursereg.jar
EXPOSE 8585
ENTRYPOINT ["java","-jar","coursereg.jar"]