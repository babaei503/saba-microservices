FROM adoptopenjdk/openjdk14:alpine-jre
VOLUME /tmp
MAINTAINER Saeid Babaei <babaei503@gmail.com>
ARG JAR_FILE
COPY ${JAR_FILE} /sb/auth-server.jar
WORKDIR /sb
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/sb/auth-server.jar"]