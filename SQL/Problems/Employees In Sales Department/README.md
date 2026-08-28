# Employees in Sales Department

## 1. Problem Statement
Write a query in SQL to find the first name, last name, department number, and department name for all employees in the 'Sales' department.

---

## 2. Approach & Logic
- Join `employees` with `departments` on `e.department_id = d.department_id`.
- Filter with `WHERE d.department_name = 'Sales'`.

---

## 3. SQL Solution

```sql
SELECT 
    e.first_name, 
    e.last_name, 
    d.department_id, 
    d.department_name
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
WHERE d.department_name = 'Sales';
```
