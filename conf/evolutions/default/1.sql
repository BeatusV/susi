# --- !Ups

CREATE TABLE USER_INFO (
  email varchar(40) PRIMARY KEY,
  first_name varchar(40) NOT NULL,
  last_name varchar(40) NOT NULL
)

# --- !Downs
DROP TABLE USER_INFO