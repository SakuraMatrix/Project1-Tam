CREATE KEYSPACE IF NOT EXISTS brokerage WITH REPLICATION = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

CREATE TABLE IF NOT EXISTS brokerage.holdings (
    symbol text,
    name text,
    price double,
    timestamp bigint,
    PRIMARY KEY (symbol, timestamp)
);