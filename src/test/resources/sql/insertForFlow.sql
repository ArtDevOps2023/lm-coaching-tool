INSERT INTO employees (id, first_name,last_name,middle_name,birthdate,gender,marital_status,company_position,date_hired,created_at,updated_at)
VALUES (1, 'Jeff','Dela Cruz','M','1990-01-01','M','M','Employee','2010-01-01',NULL,NULL);

INSERT INTO flow (flow_id, name,created_by) VALUES (1, 'Sample Flow', 'admin');

INSERT INTO employee_flow (employee_id, flow_id, sort_order) VALUES(1,1,1);

INSERT INTO section (section_id, name,color,created_by,created_date) VALUES (1,'Generic','B','Admin', NULL);

INSERT INTO flow_section (id, flow_id,section_id,sort_order) VALUES (1,1,1,1);

