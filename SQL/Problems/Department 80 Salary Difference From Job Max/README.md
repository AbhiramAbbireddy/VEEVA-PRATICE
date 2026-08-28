# Department 80 Salary Difference From Job Max

## 1. Problem Statement
Write a query in SQL to display the full name (first and last name) of employees, job title (job ID), and the salary differences to their own job for those employees who work in department ID 80.

---

## 2. Approach & Logic
- Use the window function `MAX(salary) OVER (PARTITION BY job_id) - salary` to calculate the difference from the maximum salary within that job role.
- Filter for employees in department 80: `WHERE department_id = 80`.

---

## 3. SQL Solution

```sql
SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS full_name, 
    e.job_id AS job_title, 
    MAX(e.salary) OVER (PARTITION BY e.job_id) - e.salary AS salary_diff
FROM employees AS e
WHERE e.department_id = 80;
```
