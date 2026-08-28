-- Problem 3: Display first name, last name, department ID, and department name for employees in department 80.

SELECT 
    e.first_name, 
    e.last_name, 
    d.department_id, 
    d.department_name
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
WHERE e.department_id = 80;
