FROM eclipse-temurin:17-jre-alpine

WORKDIR /usr/src/pizzeria

COPY build/libs/pizzeria-boot.jar pizzeria-boot.jar

CMD java $JAVA_OPTS -jar pizzeria-boot.jar