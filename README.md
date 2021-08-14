# Project 1: uInvest Trade
uInvest is a self directed trading service where a user can build their portfolio with unlimited fee-free trades.

## Features:
- User will be able to access the application using their web browser
- User will be able to view stock prices
- User will be able to buy/sell shares
- User will be able to add additional funds or withdraw unused funds

## Usage:
- GET /assets/{symbol} retrieves the price of the stock with symbol {symbol}
- GET /positions retrieves a list of all currently owned stocks
- POST /positions/{symbol} buys a stock with symbol {symbol} <!-- add quantity as a query? -->
- DELETE /positions sells all currently owned stock
- DELETE /positions/{symbol} sells a stock with symbol {symbol} <!-- add quantity as a query? -->

<!-- - Add a favorites/watchlist?  -->

TBA
