# Employees and Managers Including Top Level

## 1. Problem Statement
Write a query in SQL to display the first name of all employees and the first name of their manager, including those who do not work under any manager.

---

## 2. Approach & Logic
- Self-join `employees` with `employees` on `e.manager_id = m.employee_id`.
- Use a `LEFT JOIN` on `m` to preserve employees who do not report to a manager (`manager_id = 0` or `NULL`).

---

## 3. SQL Solution

```sql
SELECT 
    e.first_name AS employee_name, 
    m.first_name AS manager_name
FROM employees AS e
LEFT JOIN employees AS m 
    ON e.manager_id = m.employee_id;
```
