# All Departments Including Empty Ones

## 1. Problem Statement
Write a query in SQL to display all departments including those that do not have any employees.

---

## 2. Approach & Logic
- To include **all** departments regardless of employee assignment, `departments` must be the left table in a `LEFT JOIN` with `employees`.
- Departments with 0 employees will display `NULL` for employee attributes.

---

## 3. SQL Solution

```sql
SELECT 
    d.department_id, 
    d.department_name, 
    e.first_name, 
    e.last_name
FROM departments AS d
LEFT JOIN employees AS e 
    ON d.department_id = e.department_id;
```
