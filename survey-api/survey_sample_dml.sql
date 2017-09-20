INSERT INTO users (username,name,email,created_on) VALUES ('john','john snow','john@got.com',NOW());
INSERT INTO surveys (username) VALUES ('john');
INSERT INTO survey_details (survey_id, question, options) VALUES (1, 'Do you follow GOT?',
'{
  "a":"yes",
  "b":"no",
  "c":"not fully"
}');
INSERT INTO survey_details (survey_id, question, options) VALUES (1, 'Who is your favourite GOT charecter?',
'{
  "a":"Jon snow",
  "b":"Daenerys",
  "c":"Cersei",
  "d":"Tyrion",
  "e":"Jaime",
  "f":"Arya",
  "g":"Sansa",
  "h":"Others",
  "i":"I dont watch GOT"
}');
INSERT INTO survey_response (question_id,response) VALUES (1,'a');
INSERT INTO survey_response (question_id,response) VALUES (2,'a');
INSERT INTO survey_response (question_id,response) VALUES (1,'b');
INSERT INTO survey_response (question_id,response) VALUES (2,'i');
INSERT INTO survey_response (question_id,response) VALUES (1,'a');
INSERT INTO survey_response (question_id,response) VALUES (2,'b');
INSERT INTO survey_response (question_id,response) VALUES (1,'c');
INSERT INTO survey_response (question_id,response) VALUES (2,'d');
