# Project 1: uInvest Trade
uInvest is a self directed trading service where a user can build their portfolio with unlimited fee-free trades. Trade with $0 commissions and from the convenience of your own home.

## Features
- User will be able to look up stock prices
- User will be able to buy and sell shares
- User will be able to retrieve a list of all currently owned shares and their purchased price

## Technologies
uInvest is built in Java 8+ with the Spring Framework and Maven as the build automation tool. Reactor Netty is used for stock price lookup using an external API (HttpClient) and for the server and routes (HttpServer). Apache Cassandra was selected as the NoSQL database of choice.

## Setup

### Requirements
- Java 8
- Docker

### Database setup
TBA

### Usage
TBA

## RESTful API endpoints
- GET `/assets/{symbol}` retrieves the price of the stock with the given symbol
- GET `/positions` retrieves a list of all currently owned stocks
- POST `/positions/{symbol}` buys a share with the given symbol
- DELETE `/positions` sells all currently owned shares
- DELETE `/positions/{symbol}` sells a share with the given symbol

## Issues/TODO
- Selling currently sells all owned shares - add an optional query to specify number of shares to sell (and buy)
- Actually populate database with real information fetched from an API
- Add a watchlist page to track stocks marked as favorite?
- Aggregate results to display total quantity of a share owned + average purchase price
