FROM tomcat:8.0-jre8
EXPOSE 8080
COPY ./target/app.war /usr/local/tomcat/webapps/spring-app.war
CMD ["catalina.sh","run"]