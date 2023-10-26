FROM openjdk:11
VOLUME /colegio
EXPOSE  2011
ADD target/app-colegioS-api-0.0.1-SNAPSHOT.jar app-colegioS.jar
ENTRYPOINT ["java", "-jar", "/app-colegioS.jar"]