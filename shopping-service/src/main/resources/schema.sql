DROP TABLE IF EXISTS tlb_invoices;

CREATE TABLE tlb_invoices (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  number_invoice VARCHAR(250),
  description VARCHAR(250),
  customer_id VARCHAR(250),
  create_at DATE,
  invoice_id VARCHAR(250),
  state VARCHAR(250)
);


DROP TABLE IF EXISTS tbl_invoce_items;

CREATE TABLE tbl_invoce_items (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  quantity DOUBLE,
  price DOUBLE,
  product_id BIGINT,
  invoice_id varchar
);