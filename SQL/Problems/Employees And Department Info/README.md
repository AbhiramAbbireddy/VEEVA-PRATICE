# Employees and Department Info

## 1. Problem Statement
Write a query in SQL to display the first name, last name, department number, and department name for each employee.

---

## 2. Approach & Logic
- Join `employees` with `departments` on `e.department_id = d.department_id`.
- Use a `LEFT JOIN` on `departments` so employees who do not currently have an assigned department (`department_id = 0` or `NULL`, such as Kimberely Grant) are not filtered out.

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

---

## 4. Important Concepts Used
- **`LEFT JOIN`**: Ensures all rows from the primary table (`employees`) are preserved even when the foreign key match is missing.
