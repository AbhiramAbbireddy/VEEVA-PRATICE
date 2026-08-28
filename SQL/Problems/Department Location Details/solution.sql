-- Problem 9: Display department name, city, and state province for each department.

SELECT 
    d.department_name, 
    l.city, 
    l.state_province
FROM departments AS d
JOIN locations AS l 
    ON d.location_id = l.location_id;
