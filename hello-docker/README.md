## Minhas imagens no [Docker Hub](https://hub.docker.com/repository/docker/henriquezanetti/helloworld/general)

# Comandos docker

README criado para salvar comandos e informações sobre docker.

### Criar um container

```dockerfile
docker run -d -p 8080:8080 --name NOMECONTAINER imagem:tag
```

- O Run serve para executar o container.
- -d serve para manter o container em execução.
- -p 8080:8080 é para indicar as portas. Onde o primeiro 8080 é a porta do localhost, podemos selecionar qualquer porta que quisermos, contanto que esteja disponivel. Já a segunda porta 8080, é a porta onde o container está rodanda e nós devemos garantir que estamos informando a porta correta ou não ira funcionar.
- --name NOMECONTAINER é para nomear nosso container. Podemos escolher o nome que quiser.
- imagem:tag serve para nós informarmos o nome da imagem que está utilizando nesse container e a tag. Pode ser tanto uma imagem do docker hub como uma imagem que nós mesmo criamos.

### Baixar uma imagem do docker hub

```dockerfile
docker pull imagem:tag
```

- Exemplo de como baixar uma imagem do mysql:

```dockerfile
docker pull mysql:8.0.32
```

- onde o mysql é o nome da imagem e o 8.0.32 é a tag (podemos escolher a tag que quisermos - priorizar versões alpine)

- Exemplo de como baixar uma imagem do postgres:

```dockerfile
docker pull postgres:12.2-alpine
```

- onde o postgres é o nome da imagem e o 12.2-alpine é a tag (podemos escolher a tag que quisermos - nessa nós escolhhermos a versão alpine)

### Como criar uma imagem do meu projeto - especifico para JAVA

- Primeiro devemos gerar a pasta target onde ira conter nosso arquivo .jar. Usamos o comando:

```dockerfile
./mvnw clean install -DskipTests
```

- Após gerarmos o arquivo .jar nós podemos buildar nossa imagem com o comando:

```dockerfile
docker build -t NOMEQUEQUEREMOSPARAIMAGEM:NOSSATAG .
```

- OBS: garantir que o arquivo Dockerfile está criado e escrito de forma correta no root da nossa aplicação.
- NOMEQUEQUEREMOSPARAIMAGEM:NOSSATAG É de escolha nossa. Podemos nomear nossa imagem e tag da forma que quisermos.

### Como mandar nossa imagem para o docker hub:

```dockerfile
docker push nomedaimagem:tag
```

[Exemplos aqui](https://medium.com/trainingcenter/docker-dockerhub-pull-e-push-nas-suas-imagens-57dffa0232ad)

## Comandos uteis:

### Para ligar e desligar o service:

```dockerfile
sudo service docker start

ou

sudo service docker stop
```

### Para listar os containers em execução:

```dockerfile
docker container ls

ou

docker ps
```

### Para listar todos containers, mesmo os que estão parado:

```dockerfile
docker container ls -a

ou

docker ps -a
```

### Para listar as imagens:

```dockerfile
docker image ls
```

### Para deletar uma imagem:

```dockerfile
docker image rm -f IDENTIFICADORAQUI
```

- Nesse exemplo o -f é obrigatório quando o image está em execução, pois ele ira forçar a exclusão.
- No identificador nós podemos passar tanto o ID como o nome:tag. ex.: projeto:java

### Para deletar um container:

```dockerfile
docker rm -f IDENTIFICADORAQUI
```

- Nesse exemplo o -f é obrigatório quando o container está em execução, pois ele ira forçar a exclusão.
- No identificador nós passamos o ID do container

### Para pausar um container:

```dockerfile
docker container stop IDENTIFICADORAQUI
```

- No identificador nós passamos o ID do container

---

# Exemplos de como escrever no Dockfile:

## No React:

```Dockerfile
FROM node:alpine
WORKDIR /app
COPY . /app/
RUN npm install
EXPOSE 8080
CMD ["node", "server.js"]
```

## No Java com Spring:

### Forma 1

- Primeiro devemos garantir que a pasta target foi gerada. Caso não tenha sido, rodar o comando:

```dockerfile
./mvnw clean install -DskipTests
```

- Caso algum erro ao rodar esse comando, devemos conferir se o mvnw não está dando problema.

```Dockerfile
FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/hellodocker-0.0.1-SNAPSHOT.jar /app/
EXPOSE 9320
ENTRYPOINT ["java","-jar","/app/hellodocker-0.0.1-SNAPSHOT.jar"]
```

- Pegamos o nome de dentro do pom.xml - artifactId e acresentamos ao final "-0.0.1-SNAPSHOT.jar"

### Forma 2

```Dockerfile
FROM maven:3.8.4-openjdk-17-slim
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn package
EXPOSE 9320
CMD ["java", "-jar", "target/hellodocker-0.0.1-SNAPSHOT.jar", "--server.port=9320"]
```

- Nessa forma, a imagem fica pesada mas nós não necessitamos da pasta target.
- Pegamos o nome de dentro do pom.xml - artifactId e acresentamos ao final "-0.0.1-SNAPSHOT.jar"
