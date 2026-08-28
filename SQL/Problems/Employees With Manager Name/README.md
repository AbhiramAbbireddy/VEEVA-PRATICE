# Employees With Manager Name

## 1. Problem Statement
Write a query in SQL to display the first name of all employees including the first name of their manager.

---

## 2. Approach & Logic
- Perform a **Self-Join** on the `employees` table:
  - `e` represents the employee.
  - `m` represents the manager (`e.manager_id = m.employee_id`).
- Use a `LEFT JOIN` so top-level employees without managers (e.g. Steven King with `manager_id = 0`) are not excluded.

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
