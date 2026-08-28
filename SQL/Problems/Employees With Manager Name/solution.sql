-- Problem 8: Display first name of all employees and their manager.

SELECT 
    e.first_name AS employee_name, 
    m.first_name AS manager_name
FROM employees AS e
LEFT JOIN employees AS m 
    ON e.manager_id = m.employee_id;
