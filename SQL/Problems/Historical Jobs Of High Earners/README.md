# Historical Jobs Of High Earners

## 1. Problem Statement
Write a query in SQL to display the details of jobs (job ID) which were held by any employee who is presently earning a salary of 12,000 or more.

---

## 2. Approach & Logic
- Join `job_history` (`jh`) with `employees` (`e`) on `jh.employee_id = e.employee_id`.
- Filter for employees earning $\ge 12000$: `WHERE e.salary >= 12000`.
- Use `DISTINCT` to avoid duplicate job IDs.

---

## 3. SQL Solution

```sql
SELECT DISTINCT 
    jh.job_id
FROM job_history AS jh
JOIN employees AS e 
    ON jh.employee_id = e.employee_id
WHERE e.salary >= 12000;
```

---

## 4. Alternative Approach (Subquery with `IN`)

```sql
SELECT DISTINCT 
    job_id
FROM job_history
WHERE employee_id IN (
    SELECT employee_id 
    FROM employees 
    WHERE salary >= 12000
);
```
