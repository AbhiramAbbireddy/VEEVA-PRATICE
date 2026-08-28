-- Problem 7: Display employees earning less than employee 182.

SELECT 
    first_name, 
    last_name, 
    salary
FROM employees
WHERE salary < (
    SELECT salary 
    FROM employees 
    WHERE employee_id = 182
);
