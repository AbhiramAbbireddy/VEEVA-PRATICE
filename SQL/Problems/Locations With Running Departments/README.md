# Locations With Running Departments

## 1. Problem Statement
Write a query in SQL to display the country code (or country ID), city, and the department names which are operating there.

---

## 2. Approach & Logic
- Join `locations` with `departments` on `l.location_id = d.location_id`.
- Use an `INNER JOIN` so locations without any departments assigned are filtered out.
- Note: In this schema, the `countries` table is not present, so `l.country_id` represents the country identifier.

---

## 3. SQL Solution

```sql
SELECT 
    l.country_id AS country_code, 
    l.city, 
    d.department_name
FROM locations AS l
JOIN departments AS d 
    ON l.location_id = d.location_id;
```
