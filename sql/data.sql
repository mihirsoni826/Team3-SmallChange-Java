INSERT INTO Portfolio (ticker, avg_buy_price, quantity,user_email) VALUES ('FXAIX', 250, 8,'123@gmail.com');
INSERT INTO Portfolio (ticker, avg_buy_price, quantity,user_email) VALUES ('SPAXX', 450, 6,'123@gmail.com');
INSERT INTO Portfolio (ticker, avg_buy_price, quantity,user_email) VALUES ('AGTHX', 310, 10,'123@gmail.com');


Insert into user_details(email,date_of_birth,first_name,last_name,password,phone_number,risk_appetite) values ('123@gmail.com', '31-DEC-2000','John','Doe','123',9898989898,4);
Insert into user_details(email,date_of_birth,first_name,last_name,password,phone_number,risk_appetite) values ('ms@gmail.com', '30-NOV-2000','Mark','Smith','456',9898989898,3);


INSERT INTO bank_account_details VALUES ('1234532566', 'HDFC Bank', 10000.23, '123@gmail.com');


INSERT INTO trade_history(transaction_id, ticker, security_name, account_type, transaction_date, trade_type, asset_class, trade_price, quantity) VALUES (1, 'MSFT', 'Microsoft', 'Brokerage',TO_TIMESTAMP('2022-10-10 10:14:00', 'YYYY-MM-DD HH24:MI:SS'), 'buy','main index stocks', 155.5, 5);
INSERT INTO trade_history(transaction_id, ticker, security_name, account_type, transaction_date, trade_type, asset_class, trade_price, quantity) VALUES (2, 'TSLA', 'Tesla', 'Brokerage',TO_TIMESTAMP('2022-10-10 10:10:10', 'YYYY-MM-DD HH24:MI:SS'), 'buy','main index stocks', 200, 10);
INSERT INTO trade_history(transaction_id, ticker, security_name, account_type, transaction_date, trade_type, asset_class, trade_price, quantity) VALUES (3, 'AAPL', 'Apple', 'Brokerage',TO_TIMESTAMP('2022-10-11 08:08:00', 'YYYY-MM-DD HH24:MI:SS'), 'buy','main index stocks',100.5, 5);
INSERT INTO trade_history(transaction_id, ticker, security_name, account_type, transaction_date, trade_type, asset_class, trade_price, quantity) VALUES (4, 'GOOGL', 'Google', 'Brokerage',TO_TIMESTAMP('2022-10-11 13:10:00', 'YYYY-MM-DD HH24:MI:SS'), 'buy','main index stocks', 300, 10);
UPDATE trade_history SET user_email = '123@gmail.com';

INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('IBM','International Business Machines Corp',126.7,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('CRM','Salesforce Inc',156.12,'Corporate Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('VZ','Verizon Communications Inc',37.05,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('CVX','Chevron Corp',168.49,'main index stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('BA','Boeing Co',138.50,'Government Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('V','Visa Inc',187,'main index stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('INTC','Intel Corp',25.86,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('WMT','Walmart Inc',133.55,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('UNH','UnitedHealth Group Inc',519,'Government Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('WBA','Walgreens Boots Alliance Inc',32.32,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('AMGN','Amgen Inc',247.45,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('GS','Goldman Sachs Group Inc',310.10,'Corporate Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('MCD','McDonald''s Corp.',249.50,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('DIS','Walt Disney Co',98,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('CSCO','Cisco Systems Inc',41.45,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('MRK','Merck and Co Inc',92.97,'main index stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('DOW','Dow Inc.',44.82,'Government Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('HON','Honeywell International Inc',176.21,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('MMM','3M Co',112.85,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('KO','Coca-Cola Co.',55.07,'main index stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('TRV','Travelers Cos Inc',169.40,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('AXP','American Express Co',145.03,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('NKE','Nike Inc. Cl B',86.55,'main index stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('PG','Procter and Gamble Co',126.80,'Government Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('CAT','Caterpillar Inc',180.00,'International Market Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('HD','Home Depot Inc',268.75,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('LRCX','Lam Research Corp',366.94,'Corporate Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('T','ATandT Inc',16.63,'Government Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('DGX','Quest Diagnostics Inc',134.70,'Corporate Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('LVS','Las Vegas Sands Corp',37.79,'Small Cap Company Stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('ORCL','Oracle Corp',69.72,'main index stocks');
INSERT INTO securities  (ticker,security_name,current_market_price,asset_class) VALUES ('CCL','Carnival Corp',7.92,'Government Stocks');

UPDATE securities SET account_type = 'Brokerage', sub_account_type= 'Equity';

Insert into securities(ticker, security_name, current_market_price, asset_class, account_type, sub_account_type) values('FXAIX','Fidelity 500 Index Fund', 127.24, 'main index stocks','Brokerage','Mutual Funds');
Insert into securities(ticker, security_name, current_market_price, asset_class, account_type, sub_account_type) values('OGVXX','JPMorgan US Government Money Market Fund Capital', 1.00, 'main index stocks','Brokerage','Mutual Funds');
Insert into securities(ticker, security_name, current_market_price, asset_class, account_type, sub_account_type) values('FGTXX','Goldman Sachs FS Government Fund Institutional', 1.00,'main index stocks', 'Brokerage','Mutual Funds');
Insert into securities(ticker, security_name, current_market_price, asset_class, account_type, sub_account_type) values('SPAXX','Fidelity Government Money Market Fund', 1.00,'main index stocks', 'Brokerage','Mutual Funds');
Insert into securities(ticker, security_name, current_market_price, asset_class, account_type, sub_account_type) values('AGTHX','American Funds Growth Fund of America', 50.62,'main index stocks', 'Brokerage','Mutual Funds');


