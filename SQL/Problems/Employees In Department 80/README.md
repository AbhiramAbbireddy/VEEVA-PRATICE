# Employees in Department 80

## 1. Problem Statement
Write a query in SQL to find the first name, last name, department number, and department name for all employees in department number 80.

---

## 2. Approach & Logic
- Join `employees` with `departments` on `e.department_id = d.department_id`.
- Filter with `WHERE e.department_id = 80`.

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
WHERE e.department_id = 80;
```
