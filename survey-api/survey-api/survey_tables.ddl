CREATE TABLE users(
 username VARCHAR (50) PRIMARY KEY NOT NULL,
 name VARCHAR (50) NOT NULL,
 email VARCHAR (355) UNIQUE NOT NULL,
 created_on TIMESTAMP NOT NULL
);

CREATE TABLE surveys (
  survey_id serial PRIMARY KEY,
  username VARCHAR (50) REFERENCES users (username)
);

CREATE TABLE survey_details(
 question_id serial PRIMARY KEY NOT NULL,
 survey_id int4 REFERENCES surveys (survey_id),
 question text NOT NULL,
 options json
);

CREATE TABLE survey_response(
 question_id int4 REFERENCES survey_details (question_id),
 response_id serial NOT NULL,
 response VARCHAR(2) NOT NULL,
 comments VARCHAR(1000)
);