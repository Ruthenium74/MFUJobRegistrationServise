DROP TABLE mfu_jobs IF EXISTS;

CREATE TABLE mfu_jobs (
    id      INTEGER NOT NULL,
    type    VARCHAR(20) NOT NULL,
    user    VARCHAR(100) NOT NULL,
    device  VARCHAR(100) NOT NULL,
    amount  INTEGER NOT NULL,
    time    TIMESTAMP(0) NOT NULL,
    PRIMARY KEY (id, device)
);
