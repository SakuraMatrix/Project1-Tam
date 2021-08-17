# Project 1: uInvest Trade
uInvest is a self directed trading service where you can build your portfolio with unlimited fee-free trades. Trade with $0 commissions from the convenience of your own home.

## Features
- User can search for a stock to view their price
- User can buy and sell shares
- User can retrieve a list of all currently owned shares and their purchased price

## Technologies
uInvest is built in Java 8+ with the Spring Framework and Maven as the build automation tool. Reactor Netty is used for stock price lookup using an external API (HttpClient) and for the server and routes (HttpServer). Apache Cassandra was selected as the NoSQL database of choice.

## Setup

### Requirements
- Java 8+
- Docker

### Database setup
TBA

### Usage
TBA

## RESTful API endpoints
- GET `/assets/{symbol}` retrieves the price of the stock of the given symbol
  - Example: GET `/assets/GOOGL`
  - ![GET request to /assets/GOOGL](https://raw.githubusercontent.com/SakuraMatrix/p1-Tam-uInvest/dev/img/GET%20%E2%81%84assets%E2%81%84GOOGL.png)
- GET `/positions` retrieves a list of all currently owned stocks
  - Example: GET `/positions`
  - ![GET request to /positions](https://github.com/SakuraMatrix/p1-Tam-uInvest/blob/dev/img/GET%20%E2%81%84positions.png)
- GET `/positions/{symbol}` retrieves a list of all currently owned stocks of the given symbol
  - Example: GET `/positions/TSLA`
  - ![GET request to /positions/TSLA](https://github.com/SakuraMatrix/p1-Tam-uInvest/blob/dev/img/GET%20%E2%81%84positions%E2%81%84TSLA.png)
- POST `/positions/{symbol}` buys a share with the given symbol
- DELETE `/positions` sells all currently owned shares
- DELETE `/positions/{symbol}` sells a share with the given symbol

## Issues/Todo
- Selling currently sells all owned shares - add an optional query to specify number of shares to sell (and buy)
- Add a watchlist page to track stocks marked as favorite?
- Aggregate results to display total quantity of a share owned + average purchase price
- Logging
- Testing
- Something like dotenv to store my API key
- Update instructions on usage
