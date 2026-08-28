# Job History Between 1993 and 1997

## 1. Problem Statement
Write a query in SQL to display the job ID (job title), department name, full name (first and last name) of employee, and starting date for all the jobs which started on or after 1st January, 1993 and ended on or before 31st August, 1997.

---

## 2. Approach & Logic
- Join `job_history` (`jh`) with `employees` (`e`) on `jh.employee_id = e.employee_id`.
- Join `job_history` (`jh`) with `departments` (`d`) on `jh.department_id = d.department_id`.
- Filter dates using ISO standard date literals `'YYYY-MM-DD'` with string quotes: `jh.start_date >= '1993-01-01' AND jh.end_date <= '1997-08-31'`.

---

## 3. SQL Solution

```sql
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
```
