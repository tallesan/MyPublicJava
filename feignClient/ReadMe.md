Ключ https://openexchangerates.org/ базовый по этому програмно конвертирую курсы в RUB;
Все внешние сервисы вынесены в application.yaml
Не успел доделать настройку для docker
Примерные настройки:
FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
docker build -t springio/gs-spring-boot-docker
