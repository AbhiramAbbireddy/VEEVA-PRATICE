# Locations With Multiple Department Employees

## 1. Problem Statement
Write a query in SQL to display the country code (country ID), city, and department names where at least 2 employees are working.

---

## 2. Approach & Logic
- Join `employees` (`e`) with `departments` (`d`) on `e.department_id = d.department_id`.
- Join `departments` (`d`) with `locations` (`l`) on `d.location_id = l.location_id`.
- Group by the output dimensions: `GROUP BY l.country_id, l.city, d.department_name`.
- Filter aggregated groups with `HAVING COUNT(e.employee_id) >= 2`.

---

## 3. SQL Solution

```sql
SELECT 
    l.country_id AS country_code, 
    l.city, 
    d.department_name, 
    COUNT(e.employee_id) AS num_employees
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
JOIN locations AS l 
    ON d.location_id = l.location_id
GROUP BY 
    l.country_id, 
    l.city, 
    d.department_name
HAVING COUNT(e.employee_id) >= 2;
```
