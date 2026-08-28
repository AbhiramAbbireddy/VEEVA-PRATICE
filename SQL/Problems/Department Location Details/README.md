# Department Location Details

## 1. Problem Statement
Write a query in SQL to display the department name, city, and state province for each department.

---

## 2. Approach & Logic
- Join `departments` with `locations` on `d.location_id = l.location_id`.

---

## 3. SQL Solution

```sql
SELECT 
    d.department_name, 
    l.city, 
    l.state_province
FROM departments AS d
JOIN locations AS l 
    ON d.location_id = l.location_id;
```
