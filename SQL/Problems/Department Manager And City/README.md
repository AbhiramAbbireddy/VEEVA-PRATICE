# Department Manager and City

## 1. Problem Statement
Write a query in SQL to display the department name, full name (first and last name) of the manager, and their city.

---

## 2. Schema Context

### Table: `departments`
| Column | Type | Description |
|:---|:---|:---|
| `DEPARTMENT_ID` | `NUMERIC(4)` | Primary Key |
| `DEPARTMENT_NAME`| `VARCHAR(30)`| Name of the department |
| `MANAGER_ID` | `NUMERIC(6)` | Employee ID of the manager |
| `LOCATION_ID` | `NUMERIC(4)` | Foreign Key referencing `locations` |

### Table: `employees`
| Column | Type | Description |
|:---|:---|:---|
| `EMPLOYEE_ID` | `NUMERIC(6)` | Primary Key |
| `FIRST_NAME` | `VARCHAR(20)`| Employee's first name |
| `LAST_NAME` | `VARCHAR(25)`| Employee's last name |

### Table: `locations`
| Column | Type | Description |
|:---|:---|:---|
| `LOCATION_ID` | `NUMERIC(4)` | Primary Key |
| `CITY` | `VARCHAR(30)`| City where the department is located |

---

## 3. Approach & Logic
1. **Join `departments` and `locations`**: Connect `d.location_id = l.location_id` to retrieve the department's city.
2. **Join `departments` and `employees`**: Connect `d.manager_id = e.employee_id` to retrieve the manager's name details.
3. **Handle Missing / Unassigned Managers**:
   - In HR schemas, some departments may not have an active manager assigned (`MANAGER_ID = 0` or `NULL`).
   - Using a **`LEFT JOIN`** on `employees` ensures all departments remain visible, displaying `NULL` for departments without managers.
   - An **`INNER JOIN`** will restrict the result set to only departments with valid, existing managers.
4. **String Concatenation**: Concatenate `first_name` and `last_name` with a space separator using `CONCAT(e.first_name, ' ', e.last_name)` or the PostgreSQL string concatenation operator `||`.

---

## 4. SQL Solution

```sql
SELECT 
    d.department_name, 
    CONCAT(e.first_name, ' ', e.last_name) AS manager_name, 
    l.city
FROM departments AS d
LEFT JOIN employees AS e 
    ON d.manager_id = e.employee_id
JOIN locations AS l 
    ON d.location_id = l.location_id;
```

---

## 5. Alternative Approaches

### Alternative 1: Inner Join (Only departments with assigned managers)
```sql
SELECT 
    d.department_name, 
    e.first_name || ' ' || e.last_name AS manager_name, 
    l.city
FROM departments AS d
JOIN employees AS e 
    ON d.manager_id = e.employee_id
JOIN locations AS l 
    ON d.location_id = l.location_id;
```

### Alternative 2: Subquery in SELECT
```sql
SELECT 
    d.department_name,
    (SELECT CONCAT(e.first_name, ' ', e.last_name) 
     FROM employees AS e 
     WHERE e.employee_id = d.manager_id) AS manager_name,
    (SELECT l.city 
     FROM locations AS l 
     WHERE l.location_id = d.location_id) AS city
FROM departments AS d;
```

---

## 6. Important SQL Concepts Used
- **Multi-table `JOIN`s**: Chaining 3 tables (`departments`, `employees`, and `locations`) on foreign key relationships.
- **`LEFT JOIN` vs `INNER JOIN`**: Retaining rows from the primary table even when foreign key references are missing or unassigned.
- **String Concatenation Functions**: `CONCAT()` and the standard SQL string concatenation operator (`||`).

---

## 7. Complexity Analysis
- **Time Complexity**: $\mathcal{O}(D \log E + D \log L)$ with primary key indexes on `employees(employee_id)` and `locations(location_id)`.
- **Space Complexity**: $\mathcal{O}(D)$ for the output result set where $D$ is the number of departments.
