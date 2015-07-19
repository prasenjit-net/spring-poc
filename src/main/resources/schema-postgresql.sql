DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
  id integer NOT NULL,
  first_name character varying(255),
  last_name character varying(255),
  CONSTRAINT customer_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

DROP TABLE IF EXISTS fuser;

CREATE TABLE fuser
(
  email character varying(255) NOT NULL,
  first_name character varying(255),
  last_name character varying(255),
  CONSTRAINT fuser_pkey PRIMARY KEY (email)
)
WITH (
  OIDS=FALSE
);

DROP SEQUENCE IF EXISTS hibernate_sequence;

CREATE SEQUENCE hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;