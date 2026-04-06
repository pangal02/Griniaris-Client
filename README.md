# Griniaris Client

Client application for Griniaris.

## Requirements

- Java 21+
- Maven 3.9+

## Run in Development

```bash
mvn clean javafx:run
```

## Build Executable JAR

```bash
mvn clean package
```

Generated file:

- `target/GrinirisClient.jar`

## Run JAR

```bash
java -jar target/GrinirisClient.jar
```

At login:

- Enter the server IP (for example: `192.168.1.100`)
- Enter port `7777` (or the server port)
- Enter player name

If server and client are on different devices, both must be on a reachable network and the server port must be open.
