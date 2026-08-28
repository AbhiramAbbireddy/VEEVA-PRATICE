-- Problem 16: Display full name, job title (job ID), and salary difference to their own job for department 80.

SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS full_name, 
    e.job_id AS job_title, 
    MAX(e.salary) OVER (PARTITION BY e.job_id) - e.salary AS salary_diff
FROM employees AS e
WHERE e.department_id = 80;
