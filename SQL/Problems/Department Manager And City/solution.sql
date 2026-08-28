-- Problem: Display the department name, full name (first and last name) of manager, and their city.

SELECT 
    d.department_name, 
    CONCAT(e.first_name, ' ', e.last_name) AS manager_name, 
    l.city
FROM departments AS d
LEFT JOIN employees AS e 
    ON d.manager_id = e.employee_id
JOIN locations AS l 
    ON d.location_id = l.location_id;
