# Average Salary Per Job Title

## 1. Problem Statement
Write a query in SQL to display the job title (job ID) and average salary of employees.

---

## 2. Approach & Logic
- Group employees by their `job_id`: `GROUP BY job_id`.
- Calculate the average salary: `AVG(salary)`.

---

## 3. SQL Solution

```sql
SELECT 
    job_id AS job_title, 
    AVG(salary) AS avg_salary
FROM employees
GROUP BY job_id;
```
