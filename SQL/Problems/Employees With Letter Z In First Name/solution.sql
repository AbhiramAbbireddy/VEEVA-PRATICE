-- Problem 5: Display employees containing letter 'z' in their first name.

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
    ON d.location_id = l.location_id
WHERE e.first_name ILIKE '%z%';
