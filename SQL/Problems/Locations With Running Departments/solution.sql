-- Problem 17: Display country code, city, and departments running there.

SELECT 
    l.country_id AS country_code, 
    l.city, 
    d.department_name
FROM locations AS l
JOIN departments AS d 
    ON l.location_id = d.location_id;
