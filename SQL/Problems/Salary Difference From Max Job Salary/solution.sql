-- Problem 14: Display job ID, employee full name, and difference from max salary for that job.

SELECT 
    e.job_id AS job_title, 
    CONCAT(e.first_name, ' ', e.last_name) AS full_name, 
    MAX(e.salary) OVER (PARTITION BY e.job_id) - e.salary AS salary_diff
FROM employees AS e;
