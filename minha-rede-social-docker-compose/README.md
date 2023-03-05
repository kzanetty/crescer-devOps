### outra forma de fazer:

- Dockerfile da api:

```yml
FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /app
COPY ./api/src /app/src
COPY ./api/pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /app/target/berk-0.0.1-SNAPSHOT.jar /usr/local/lib/berk-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/berk-0.0.1-SNAPSHOT.jar"]
```

- Dockerfile do app:

```yml
FROM node:alpine
WORKDIR /app
COPY ./app /app/
RUN npm ci
RUN npm run build
EXPOSE 8080
CMD [ "npx", "serve", "build" ]
```

- Docker-compose:

```yml
version: "3.1"

services:
  PostgreSQL:
    image: postgres:latest
    ports:
      - "5432:5432"
    environment:
      - POSTGRES_PASSWORD=1234
      - POSTGRES_USER=postgres
      - POSTGRES_DB=berk
    volumes:
      - postgresdb_volume:/var/lib/postgresql/data
      - ./api/data/schema.sql:/docker-entrypoint-initdb.d/schema.sql
      - ./api/data/script.sql:/docker-entrypoint-initdb.d/script.sql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres"]
      interval: 10s
      timeout: 5s
      retries: 5

  api:
    build:
      context: .
      dockerfile: ./api/Dockerfile
    ports:
      - "8080:8080"
    depends_on:
      PostgreSQL:
        condition: service_healthy
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://PostgreSQL:5432/berk
      - SPRING_DATASOURCE_USERNAME=postgres
      - SPRING_DATASOURCE_PASSWORD=1234
      - SPRING_JPA_HIBERNATE_DDL_AUTO=validate

  front:
    build:
      context: .
      dockerfile: ./app/Dockerfile
    ports:
      - "3000:3000"

volumes:
  postgresdb_volume:
```
