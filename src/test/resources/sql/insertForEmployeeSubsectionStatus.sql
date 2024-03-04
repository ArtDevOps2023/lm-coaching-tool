INSERT INTO employees (id, first_name,last_name,middle_name,birthdate,gender,marital_status,company_position,date_hired,created_at,updated_at)
VALUES (1, 'Jeff','Dela Cruz','M','1990-01-01','M','M','Employee','2010-01-01',NULL,NULL);

INSERT INTO section (section_id, name,color,created_by,created_date) VALUES (1,'Generic','B','Admin', NULL);

INSERT INTO subsection (subsection_id, section_id, description, facilitator, details, target_day, target_sprint)
VALUES (1,1,'Work logs discussion','Operations Analyst','Attendance Notification','Day 1','Sprint 1');

INSERT INTO employee_subsection_status (id, employee_id, subsection_id, status, start_date, completed_date)
VALUES (1,1,1,'Ongoing','2023-12-15','2023-12-15');