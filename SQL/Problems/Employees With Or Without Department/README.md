# Employees With Or Without Department

## 1. Problem Statement
Write a query in SQL to display the first name, last name, department number, and name for all employees who have or have not any department.

---

## 2. Approach & Logic
- Use a `LEFT JOIN` starting from `employees` joining `departments` on `e.department_id = d.department_id`.
- Retains employees with `department_id = 0` or `NULL`.

---

## 3. SQL Solution

```sql
SELECT 
    e.first_name, 
    e.last_name, 
    d.department_id, 
    d.department_name
FROM employees AS e
LEFT JOIN departments AS d 
    ON e.department_id = d.department_id;
```
