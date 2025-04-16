# Photo Spring Boot App

- To learn spring boot
- H2 database
- RabbitMQ

## Run from IJ IDE

- Make package `mvn clean package`
- And cd to : `target\photo-0.0.1-SNAPSHOT.jar` which is `26MB`
- Run jar file: `java -jar  photo-0.0.1-SNAPSHOT.jar`

## Endpoints

- <http://localhost:8080/upload.html>
- <http://localhost:8080/photo>
- <http://localhost:8080/photo/1>
- Run CRUD delete and post from browsers -> Resources -> New Snippet
- The snippets are in crud.js
- <http://localhost:8080/h2-console>
  - With JDBC URL: <jdbc:h2:~/dbphoto> and blank username and password

## RabbitMQ

- Docker pull `docker pull rabbitmq:4.0.9-management`
- Docker run `docker run --rm -ti -p 15672:15672 -p 5672:5672 rabbitmq:4.0.9-management`
- Browse to: <http://localhost:15672/> with username and password: `guest`

## Languages

- Add config: LocaleConfig
- Add messages.properties and related files in resources(bundle) with keys
- Add key in thymeleaf as `th:text="#{page.title}"`

## Tips

- Tilde(~) character: `OPTION + ^` and then space
- Credits: <https://www.youtube.com/watch?v=QuvS_VLbGko>
