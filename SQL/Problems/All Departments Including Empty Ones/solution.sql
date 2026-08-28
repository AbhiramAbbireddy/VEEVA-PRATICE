-- Problem 6: Display all departments including empty ones.

SELECT 
    d.department_id, 
    d.department_name, 
    e.first_name, 
    e.last_name
FROM departments AS d
LEFT JOIN employees AS e 
    ON d.department_id = e.department_id;
