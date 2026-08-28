# Employees Earning Less Than Employee 182

## 1. Problem Statement
Write a query in SQL to display the first and last name and salary for those employees who earn less than the employee whose employee ID is 182.

---

## 2. Approach & Logic
- Use a scalar subquery to find the salary of employee 182: `SELECT salary FROM employees WHERE employee_id = 182`.
- Filter the outer query using `WHERE salary < (...)`.

---

## 3. SQL Solution

```sql
SELECT 
    first_name, 
    last_name, 
    salary
FROM employees
WHERE salary < (
    SELECT salary 
    FROM employees 
    WHERE employee_id = 182
);
```
