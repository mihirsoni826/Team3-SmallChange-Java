drop table securities;
drop table portfolio;
drop table trade_history;
drop table user_details;
drop table bank_account_details;

CREATE TABLE Securities (
    ticker VARCHAR(5) NOT NULL,
    security_name VARCHAR(50) NOT NULL,
    current_market_price NUMBER(8,3) NOT NULL,
    asset_class VARCHAR(30) NOT NULL,
    PRIMARY KEY (ticker)
);
ALTER TABLE Securities MODIFY current_market_price NUMBER (8,3);
ALTER TABLE securities ADD account_type VARCHAR(25);
ALTER TABLE securities ADD sub_account_type VARCHAR(20);

CREATE TABLE user_details (
    user_email VARCHAR(30) NOT NULL,
    user_password VARCHAR(20) NOT NULL,
    user_name VARCHAR(30) NOT NULL,
    user_dob DATE NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (user_email)
);

Create table trade_history (
    transaction_id INT NOT NULL,
    ticker varchar(10) not null,
    security_name varchar(50) not null,
    account_type VARCHAR(10) NOT NULL,
    transaction_date TIMESTAMP NOT NULL,
    trade_type VARCHAR(4) NOT NULL,
    asset_class VARCHAR(30) NOT NULL,
    trade_price NUMBER(8,3) NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT transaction_id_pk PRIMARY KEY (transaction_id)
);
ALTER TABLE trade_history ADD  user_email varchar2(30);
ALTER TABLE trade_history ADD CONSTRAINT t_user_fk FOREIGN KEY (user_email) REFERENCES user_details (user_email);

CREATE TABLE Portfolio (
    ticker VARCHAR(5) NOT NULL,
    avg_buy_price NUMBER(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    CONSTRAINT ticker_pk PRIMARY KEY (ticker),
    CONSTRAINT ticker_fk FOREIGN KEY (ticker) REFERENCES Securities(ticker)
);
ALTER TABLE portfolio ADD  user_email varchar2(30);
ALTER TABLE portfolio ADD CONSTRAINT user_fk FOREIGN KEY (user_email) REFERENCES user_details (user_email);


CREATE TABLE bank_account_details (
    account_number VARCHAR(10) NOT NULL,
    bank_name VARCHAR(30) NOT NULL,
    balance NUMBER(8,2) NOT NULL,
    email VARCHAR(30) NOT NULL,
    PRIMARY KEY (account_number),
    FOREIGN KEY (email) REFERENCES user_details(user_email)
);

