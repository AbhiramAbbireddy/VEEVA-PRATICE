-- Problem 23: Display employee ID, job name (job ID), and days worked for jobs in department 80.

SELECT 
    e.employee_id, 
    jh.job_id AS job_name, 
    (jh.end_date - jh.start_date) AS days_worked
FROM employees AS e
JOIN job_history AS jh 
    ON e.employee_id = jh.employee_id
WHERE jh.department_id = 80;
