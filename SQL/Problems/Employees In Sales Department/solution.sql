-- Problem 4: Display employees in the 'Sales' department.

SELECT 
    e.first_name, 
    e.last_name, 
    d.department_id, 
    d.department_name
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
WHERE d.department_name = 'Sales';
