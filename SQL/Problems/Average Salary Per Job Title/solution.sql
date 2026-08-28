-- Problem 19: Display job title (job ID) and average salary of employees.

SELECT 
    job_id AS job_title, 
    AVG(salary) AS avg_salary
FROM employees
GROUP BY job_id;
