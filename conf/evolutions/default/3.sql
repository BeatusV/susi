# --- !Ups

ALTER TABLE account RENAME logininfoemail to email;
ALTER TABLE account ADD PRIMARY KEY (email);

# --- !Downs
ALTER TABLE account RENAME email to logininnfoemail