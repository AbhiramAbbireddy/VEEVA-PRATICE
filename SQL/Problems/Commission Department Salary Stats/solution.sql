-- Problem 15: Display department name, average salary, and count of commission-earning employees.

SELECT 
    d.department_name, 
    AVG(e.salary) AS avg_salary, 
    COUNT(e.employee_id) AS num_employees
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
WHERE e.commission_pct > 0 
GROUP BY d.department_id, d.department_name;
