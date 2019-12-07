FROM openjdk:11.0.5-jre-stretch

COPY ./release/target/release-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -jar release-0.0.1-SNAPSHOT.jar