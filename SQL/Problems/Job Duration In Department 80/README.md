# Job Duration in Department 80

## 1. Problem Statement
Write a query in SQL to display the employee ID, job name (job ID), and number of days worked for all those historical jobs in department 80.

---

## 2. Approach & Logic
- In PostgreSQL, subtracting two dates (`end_date - start_date`) directly computes the difference in days as an integer.
- Join `employees` with `job_history` on `e.employee_id = jh.employee_id`.
- Filter for jobs in department 80: `WHERE jh.department_id = 80`.

---

## 3. SQL Solution

```sql
SELECT 
    e.employee_id, 
    jh.job_id AS job_name, 
    (jh.end_date - jh.start_date) AS days_worked
FROM employees AS e
JOIN job_history AS jh 
    ON e.employee_id = jh.employee_id
WHERE jh.department_id = 80;
```
