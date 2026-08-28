# Colleagues in Same Department as Taylor

## 1. Problem Statement
Write a query in SQL to display the first name, last name, and department number for those employees who work in the same department as the employee who holds the last name 'Taylor'.

---

## 2. Approach & Logic
- Since multiple employees may share the last name 'Taylor' across different departments, the subquery `SELECT department_id FROM employees WHERE last_name = 'Taylor'` may return multiple department IDs.
- Use the **`IN`** operator in the `WHERE` clause rather than `=` to handle multi-value subquery results safely.

---

## 3. SQL Solution

```sql
SELECT 
    first_name, 
    last_name, 
    department_id
FROM employees
WHERE department_id IN (
    SELECT department_id 
    FROM employees 
    WHERE last_name = 'Taylor'
);
```
