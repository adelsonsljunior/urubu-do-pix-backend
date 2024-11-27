# urubu-do-pix-backend

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

<br>

> ## How to run

### Create a copy of the `.env.example` file named `.env`

### Run database container

~~~shell
docker compose up
~~~

### Packaging and running the application

```shell script
./mvnw package
```

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

<br>

> ## Accessing documentation with swagger

### `localhost:8080/q/swagger-ui`

<br>

![image](https://github.com/user-attachments/assets/e6fbe632-38ce-4206-a8e0-160e328dc4f2)
