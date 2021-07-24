FROM gradle:7-jdk16-hotspot AS build
WORKDIR /app
COPY . .
RUN gradle build

FROM tomcat:9-jdk16-openjdk-slim
COPY --from=build /app/build/libs/webmvc-1.0.war $CATALINA_HOME/webapps/ROOT.war
EXPOSE 8080
