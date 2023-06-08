FROM openjdk:17
MAINTAINER MaciejBugaj
COPY target/book_coach_be-0.0.1-SNAPSHOT.jar bookcoachbe-1.0.0.jar
ENTRYPOINT ["java", "-jar","/bookcoachbe-1.0.0.jar"]