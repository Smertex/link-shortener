CREATE SCHEMA IF NOT EXISTS link_shortener;

CREATE SEQUENCE IF NOT EXISTS link_seq START WITH 1 INCREMENT BY 3;

CREATE TABLE IF NOT EXISTS link_shortener.link
(
    id          BIGINT      PRIMARY KEY         DEFAULT NEXTVAL('link_seq'),
    full_link   TEXT        NOT NULL    UNIQUE,
    short_link  TEXT        NOT NULL    UNIQUE
);