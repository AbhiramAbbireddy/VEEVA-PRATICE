# Salary Difference From Max Job Salary

## 1. Problem Statement
Write a query in SQL to display job title (job ID), full name (first and last name) of employee, and the difference between maximum salary for the job and salary of the employee.

---

## 2. Approach & Logic
Since the environment does not contain a separate `jobs` table, the maximum salary per job is computed dynamically from active employees.

### Method 1 (Recommended): Window Functions (`OVER PARTITION BY`)
- `MAX(e.salary) OVER (PARTITION BY e.job_id)` computes the maximum salary for each `job_id` partition without collapsing rows, executing in a single efficient scan.

### Method 2: Correlated Subquery
- `(SELECT MAX(ee.salary) FROM employees ee WHERE ee.job_id = e.job_id) - e.salary` computes the maximum per row.

---

## 3. SQL Solution

```sql
SELECT 
    e.job_id AS job_title, 
    CONCAT(e.first_name, ' ', e.last_name) AS full_name, 
    MAX(e.salary) OVER (PARTITION BY e.job_id) - e.salary AS salary_diff
FROM employees AS e;
```

---

## 4. Alternative Approach (Correlated Subquery)

```sql
SELECT 
    e.job_id AS job_title, 
    CONCAT(e.first_name, ' ', e.last_name) AS full_name, 
    ((SELECT MAX(ee.salary) 
      FROM employees AS ee 
      WHERE ee.job_id = e.job_id) - e.salary) AS salary_diff
FROM employees AS e;
```
