-- Problem 13: Display historical jobs started on/after 1993-01-01 and ended on/before 1997-08-31.

SELECT 
    jh.job_id AS job_title, 
    d.department_name, 
    CONCAT(e.first_name, ' ', e.last_name) AS full_name, 
    jh.start_date
FROM job_history AS jh
JOIN employees AS e 
    ON jh.employee_id = e.employee_id
JOIN departments AS d 
    ON jh.department_id = d.department_id
WHERE jh.start_date >= '1993-01-01' 
  AND jh.end_date <= '1997-08-31';
