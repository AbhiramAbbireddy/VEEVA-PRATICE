# Employee Department City and State

## 1. Problem Statement
Write a query in SQL to display the first and last name, department, city, and state province for each employee.

---

## 2. Approach & Logic
- Join `employees` with `departments` on `e.department_id = d.department_id`.
- Join `departments` with `locations` on `d.location_id = l.location_id`.
- Use `LEFT JOIN` for both tables to guarantee employees without departments (e.g. `department_id = 0`) are retained in the result set.

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
    ON d.location_id = l.location_id;
```

---

## 4. Important Concepts Used
- **Chained `LEFT JOIN`s**: Maintaining row integrity across multiple joins.
