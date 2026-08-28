-- Problem 20: Display distinct historical jobs of employees presently earning >= 12000.

SELECT DISTINCT 
    jh.job_id
FROM job_history AS jh
JOIN employees AS e 
    ON jh.employee_id = e.employee_id
WHERE e.salary >= 12000;
