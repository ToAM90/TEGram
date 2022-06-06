BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, accounts, posts, likes, favorites, comments;
DROP SEQUENCE IF EXISTS seq_user_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE accounts (
	account_id serial,
	profile_img varchar(500),
	user_id int NOT NULL,
	display_name varchar(30),
	biography varchar (300),
	CONSTRAINT PK_account PRIMARY KEY (account_id),
	CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)

);


CREATE TABLE posts (
	post_id serial,
	account_id int NOT NULL,
	img varchar(100),
	caption varchar(200),
	post_date TIMESTAMP,
	privated boolean,
	CONSTRAINT PK_post PRIMARY KEY (post_id),
	CONSTRAINT FK_account FOREIGN KEY (account_id) REFERENCES accounts(account_id)

);


CREATE TABLE likes(
	account_id int NOT NULL,
	post_id int NOT NULL,
	CONSTRAINT FK_account FOREIGN KEY (account_id) REFERENCES accounts(account_id),
	CONSTRAINT FK_post FOREIGN KEY (post_id) REFERENCES posts(post_id),
	CONSTRAINT PK_like_post_account PRIMARY KEY (account_id, post_id)
);

CREATE TABLE favorites(
	account_id int NOT NULL,
	post_id int NOT NULL,
	CONSTRAINT FK_account FOREIGN KEY (account_id) REFERENCES accounts(account_id),
	CONSTRAINT FK_post FOREIGN KEY (post_id) REFERENCES posts(post_id),
	CONSTRAINT PK_favorite_post_account PRIMARY KEY (account_id, post_id)
);

CREATE TABLE comments (
	comment_id serial,
	account_id int NOT NULL,
	post_id int NOT NULL,
	comment_text varchar(500),
	CONSTRAINT PK_comment PRIMARY KEY (comment_id),
	CONSTRAINT FK_post FOREIGN KEY (post_id) REFERENCES posts(post_id),
	CONSTRAINT FK_account FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);


INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;