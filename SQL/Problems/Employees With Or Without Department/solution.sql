-- Problem 10: Display employees who have or have not any department.

SELECT 
    e.first_name, 
    e.last_name, 
    d.department_id, 
    d.department_name
FROM employees AS e
LEFT JOIN departments AS d 
    ON e.department_id = d.department_id;
