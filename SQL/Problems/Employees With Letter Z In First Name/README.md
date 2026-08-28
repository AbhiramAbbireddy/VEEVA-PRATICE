# Employees With Letter Z In First Name

## 1. Problem Statement
Write a query in SQL to display those employees who contain a letter 'z' in their first name, and also display their last name, department, city, and state province.

---

## 2. Approach & Logic
- Join `employees` with `departments` on `e.department_id = d.department_id`.
- Join `departments` with `locations` on `d.location_id = l.location_id`.
- Use `LEFT JOIN`s to ensure no employees are lost.
- Filter with `WHERE e.first_name ILIKE '%z%'` (case-insensitive search in PostgreSQL) or `LIKE '%z%'`.

---

## 3. SQL Solution

```sql
SELECT 
    e.first_name, 
    e.last_name, 
    d.department_name, 
    l.city, 
    l.state_province
FROM employees AS e
LEFT JOIN departments AS d 
    ON e.department_id = d.department_id
LEFT JOIN locations AS l 
    ON d.location_id = l.location_id
WHERE e.first_name ILIKE '%z%';
```

---

## 4. Important Concepts Used
- **`ILIKE` in PostgreSQL**: Case-insensitive pattern matching.
