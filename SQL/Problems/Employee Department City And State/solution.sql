-- Problem 2: Display first and last name, department, city, and state province for each employee.

SELECT 
    e.first_name, 
    e.last_name, 
    d.department_name, 
    l.city, 
    l.state_province
FROM employees AS e
LEFT JOIN departments AS d 
    ON e.department_id = d.department_id
LEFT JOIN locations AS l 
    ON d.location_id = l.location_id;
