# Project 1: uInvest
uInvest is a self directed trading service where you can build your portfolio with unlimited fee-free trades. Trade with $0 commissions from the convenience of your own home.

## Features
- User can search for a stock to view its price
- User can buy and sell shares
- User can retrieve a list of all currently owned shares and their purchased price

## Technology Stack
* Java 8+
* Maven
* Spring
* Reactor Netty
* Cassandra

## Install

### Requirements
- Java 8+
- Maven
- Docker

### Database setup
* Clone this repository
```
$ git clone git@github.com:SakuraMatrix/p1-Tam-uInvest.git
$ cd p1-Tam-uInvest
```
* Create and start a Cassandra container in Docker connected to port 9042
```
$ docker run -it -p 9042:9042 --name cassandra cassandra
```
* Copy the schema.cql file to the Cassandra container
```
$ docker cp src/main/resources/schema.cql cassandra:schema.cql
```
* Execute the schema.cql file in the Cassandra container
```
$ docker exec -it cassandra bash
$ cqlsh -f schema.cql
```
* For future instances, the container should be started with the command
```
$ docker start cassandra
```

### Configure API key
This project uses a free API from Financial Modeling Prep to look up stock prices. You can obtain a free API key at https://financialmodelingprep.com/developer

Create a `.env` file in `src/main/resources/` with the following contents:
```
API_KEY="YOUR_API_KEY_HERE"
```

### Usage
Compile and package the code
```
$ mvn compile assembly:single
```
This will create a JAR file at `target/uinvest-{version}-jar-with-dependencies.jar`

You can execute the JAR file with
```
$ java -jar target/uinvest-{version}-jar-with-dependencies.jar
```

## RESTful API endpoints
- GET `/assets/{symbol}` retrieves the price of the stock of the given symbol
  - Example: GET `/assets/GOOGL`
  - ![GET request to /assets/GOOGL](https://raw.githubusercontent.com/SakuraMatrix/p1-Tam-uInvest/reactor-netty-final/img/GET%20%E2%81%84assets%E2%81%84GOOGL.png)
- GET `/positions` retrieves a list of all currently owned stocks
  - Example: GET `/positions`
  - ![GET request to /positions](https://raw.githubusercontent.com/SakuraMatrix/p1-Tam-uInvest/reactor-netty-final/img/GET%20%E2%81%84positions.png)
- GET `/positions/{symbol}` retrieves a list of all currently owned stocks of the given symbol
  - Example: GET `/positions/TSLA`
  - ![GET request to /positions/TSLA](https://raw.githubusercontent.com/SakuraMatrix/p1-Tam-uInvest/reactor-netty-final/img/GET%20%E2%81%84positions%E2%81%84TSLA.png)
- POST `/positions/{symbol}` buys a share with the given symbol
- DELETE `/positions` sells all currently owned shares
- DELETE `/positions/{symbol}` sells all shares with the given symbol

## Issues/Todo
- Watchlist page to track stocks marked as favorite?
- Aggregate results to display total quantity of a share owned + average purchase price
- Additional queries, such as displaying max price, etc.
- Endpoint to sell only one share (identified by timestamp?)
- Unit tests
- Configure and deploy to AWS (both database and application)
- Spring PropertySource / application.properties
