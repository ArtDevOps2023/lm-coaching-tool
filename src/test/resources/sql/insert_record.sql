INSERT INTO employees (id, first_name,last_name,middle_name,birthdate,gender,marital_status,company_position,date_hired,created_at,updated_at) 
VALUES (1, 'Juan','Dela Cruz','M','1990-01-01','M','M','Employee','2010-01-01',NULL,NULL);

INSERT INTO contacts (employee_id,contact_no,is_primary) VALUES(1,'555-55555',1);

INSERT INTO addresses (employee_id,address1,address2,is_primary) VALUES(1,'123 street','lapulapu city', 0);


