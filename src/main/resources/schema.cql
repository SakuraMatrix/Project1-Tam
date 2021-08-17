CREATE KEYSPACE IF NOT EXISTS brokerage WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE IF NOT EXISTS brokerage.holdings (
    symbol text,
    price double,
    PRIMARY KEY (symbol, price)
);

INSERT INTO brokerage.holdings (symbol, price) VALUES ('AMD', 110.55);
INSERT INTO brokerage.holdings (symbol, price) VALUES ('GME', 162.52);
INSERT INTO brokerage.holdings (symbol, price) VALUES ('GME', 419.99);