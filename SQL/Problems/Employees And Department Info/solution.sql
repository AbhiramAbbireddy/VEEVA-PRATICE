-- Problem 1: Display first name, last name, department number, and department name for each employee.

SELECT 
    e.first_name, 
    e.last_name, 
    d.department_id, 
    d.department_name
FROM employees AS e
LEFT JOIN departments AS d 
    ON e.department_id = d.department_id;
