--chagaiNikita

CREATE TABLE currency_rate
(
    id      BIGSERIAL PRIMARY KEY,
    code    VARCHAR(10)    NOT NULL,
    name    VARCHAR(100)   NOT NULL,
    nominal INTEGER        NOT NULL,
    rate    DECIMAL(19, 4) NOT NULL,
    date    DATE           NOT NULL,

    CONSTRAINT uq_currency_rate_code_date UNIQUE (code, date)
);