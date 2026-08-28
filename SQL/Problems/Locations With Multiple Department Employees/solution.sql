-- Problem 21: Display country code, city, and department names where >= 2 employees work.

SELECT 
    l.country_id AS country_code, 
    l.city, 
    d.department_name, 
    COUNT(e.employee_id) AS num_employees
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
JOIN locations AS l 
    ON d.location_id = l.location_id
GROUP BY 
    l.country_id, 
    l.city, 
    d.department_name
HAVING COUNT(e.employee_id) >= 2;
