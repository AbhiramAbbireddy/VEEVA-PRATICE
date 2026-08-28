-- Problem 12: Display employees in the same department as any employee with last name 'Taylor'.

SELECT 
    first_name, 
    last_name, 
    department_id
FROM employees
WHERE department_id IN (
    SELECT department_id 
    FROM employees 
    WHERE last_name = 'Taylor'
);
