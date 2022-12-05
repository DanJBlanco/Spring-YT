
DROP TABLE IF EXISTS TBL_REGIONS;

CREATE TABLE TBL_REGIONS(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR
);

DROP TABLE IF EXISTS tbl_customers;

CREATE TABLE tbl_customers (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    number_id varchar,
    first_name varchar,
    last_name varchar,
    email varchar,
    photo_url varchar,
    region_id varchar,
    state varchar
);
