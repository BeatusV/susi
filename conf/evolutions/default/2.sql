# --- !Ups

CREATE TABLE account (
    logininfoemail character varying(255) NOT NULL,
    hasher character varying(255) NOT NULL,
    salt character varying(255),
    password character varying(255) NOT NULL
);

ALTER TABLE public.account OWNER TO postgres;
# --- !Downs
DROP TABLE account