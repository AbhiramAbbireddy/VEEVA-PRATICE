# Commission Department Salary Stats

## 1. Problem Statement
Write a query in SQL to display the name of the department, average salary, and number of employees working in that department who got commission.

---

## 2. Approach & Logic
- Join `employees` with `departments` on `e.department_id = d.department_id`.
- Filter for employees receiving commissions: `WHERE e.commission_pct > 0`.
- Group by department: `GROUP BY d.department_id, d.department_name`.
- Calculate aggregate metrics: `AVG(e.salary)` and `COUNT(e.employee_id)`.

---

## 3. SQL Solution

```sql
SELECT 
    d.department_name, 
    AVG(e.salary) AS avg_salary, 
    COUNT(e.employee_id) AS num_employees
FROM employees AS e
JOIN departments AS d 
    ON e.department_id = d.department_id
WHERE e.commission_pct > 0 
GROUP BY d.department_id, d.department_name;
```
