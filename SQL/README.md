# 🗄️ SQL Mastery & Problem Bank

A structured repository containing solved SQL interview problems, queries, and conceptual notes based on the PostgreSQL HR schema.

---

## 📊 Database Schema

The queries in this repository operate on an accurate 4-table schema:
- [`SQL/Schema/setup.sql`](./Schema/setup.sql): Complete PostgreSQL initialization and data insertion script.
- [`SQL/Schema/README.md`](./Schema/README.md): Schema diagram, table definitions, and constraints.

---

## 📁 Solved Problems Index

| # | Problem Name | Concepts Tested | Solution |
|:---:|:---|:---|:---:|
| 1 | [Employees and Department Info](./Problems/Employees%20And%20Department%20Info/) | `LEFT JOIN` on departments | [solution.sql](./Problems/Employees%20And%20Department%20Info/solution.sql) |
| 2 | [Employee Department City and State](./Problems/Employee%20Department%20City%20And%20State/) | Chained `LEFT JOIN`s across 3 tables | [solution.sql](./Problems/Employee%20Department%20City%20And%20State/solution.sql) |
| 3 | [Employees in Department 80](./Problems/Employees%20In%20Department%2080/) | `JOIN`, `WHERE` filtering | [solution.sql](./Problems/Employees%20In%20Department%2080/solution.sql) |
| 4 | [Employees in Sales Department](./Problems/Employees%20In%20Sales%20Department/) | `JOIN`, department name filter | [solution.sql](./Problems/Employees%20In%20Sales%20Department/solution.sql) |
| 5 | [Employees With Letter Z In First Name](./Problems/Employees%20With%20Letter%20Z%20In%20First%20Name/) | PostgreSQL `ILIKE` case-insensitive matching | [solution.sql](./Problems/Employees%20With%20Letter%20Z%20In%20First%20Name/solution.sql) |
| 6 | [All Departments Including Empty Ones](./Problems/All%20Departments%20Including%20Empty%20Ones/) | `LEFT JOIN` from departments | [solution.sql](./Problems/All%20Departments%20Including%20Empty%20Ones/solution.sql) |
| 7 | [Employees Earning Less Than Employee 182](./Problems/Employees%20Earning%20Less%20Than%20Employee%20182/) | Scalar subquery comparison | [solution.sql](./Problems/Employees%20Earning%20Less%20Than%20Employee%20182/solution.sql) |
| 8 | [Employees With Manager Name](./Problems/Employees%20With%20Manager%20Name/) | `Self-Join`, `LEFT JOIN` | [solution.sql](./Problems/Employees%20With%20Manager%20Name/solution.sql) |
| 9 | [Department Location Details](./Problems/Department%20Location%20Details/) | Two-table `INNER JOIN` | [solution.sql](./Problems/Department%20Location%20Details/solution.sql) |
| 10 | [Employees With Or Without Department](./Problems/Employees%20With%20Or%20Without%20Department/) | `LEFT JOIN` preserving unassigned staff | [solution.sql](./Problems/Employees%20With%20Or%20Without%20Department/solution.sql) |
| 11 | [Employees and Managers Including Top Level](./Problems/Employees%20And%20Managers%20Including%20Top%20Level/) | `Self-Join` with top-level manager preservation | [solution.sql](./Problems/Employees%20And%20Managers%20Including%20Top%20Level/solution.sql) |
| 12 | [Colleagues in Same Department as Taylor](./Problems/Colleagues%20In%20Same%20Department%20As%20Taylor/) | Subquery with `IN` operator | [solution.sql](./Problems/Colleagues%20In%20Same%20Department%20As%20Taylor/solution.sql) |
| 13 | [Job History Between 1993 and 1997](./Problems/Job%20History%20Between%201993%20And%201997/) | Three-table join, ISO date ranges | [solution.sql](./Problems/Job%20History%20Between%201993%20And%201997/solution.sql) |
| 14 | [Salary Difference From Max Job Salary](./Problems/Salary%20Difference%20From%20Max%20Job%20Salary/) | Window Function `MAX() OVER (PARTITION BY)` | [solution.sql](./Problems/Salary%20Difference%20From%20Max%20Job%20Salary/solution.sql) |
| 15 | [Commission Department Salary Stats](./Problems/Commission%20Department%20Salary%20Stats/) | `GROUP BY`, `AVG`, `COUNT`, `commission_pct > 0` | [solution.sql](./Problems/Commission%20Department%20Salary%20Stats/solution.sql) |
| 16 | [Department 80 Salary Difference From Job Max](./Problems/Department%2080%20Salary%20Difference%20From%20Job%20Max/) | Window Function + `WHERE` filter | [solution.sql](./Problems/Department%2080%20Salary%20Difference%20From%20Job%20Max/solution.sql) |
| 17 | [Locations With Running Departments](./Problems/Locations%20With%20Running%20Departments/) | Two-table `INNER JOIN` | [solution.sql](./Problems/Locations%20With%20Running%20Departments/solution.sql) |
| 19 | [Average Salary Per Job Title](./Problems/Average%20Salary%20Per%20Job%20Title/) | `GROUP BY job_id`, `AVG(salary)` | [solution.sql](./Problems/Average%20Salary%20Per%20Job%20Title/solution.sql) |
| 20 | [Historical Jobs Of High Earners](./Problems/Historical%20Jobs%20Of%20High%20Earners/) | `DISTINCT`, `JOIN`, `WHERE salary >= 12000` | [solution.sql](./Problems/Historical%20Jobs%20Of%20High%20Earners/solution.sql) |
| 21 | [Locations With Multiple Department Employees](./Problems/Locations%20With%20Multiple%20Department%20Employees/) | Three-table join, `GROUP BY`, `HAVING COUNT >= 2` | [solution.sql](./Problems/Locations%20With%20Multiple%20Department%20Employees/solution.sql) |
| 22 | [Department Manager and City](./Problems/Department%20Manager%20And%20City/) | Three-table join, `LEFT JOIN` on manager, `CONCAT` | [solution.sql](./Problems/Department%20Manager%20And%20City/solution.sql) |
| 23 | [Job Duration in Department 80](./Problems/Job%20Duration%20In%20Department%2080/) | PostgreSQL Date subtraction `(end_date - start_date)` | [solution.sql](./Problems/Job%20Duration%20In%20Department%2080/solution.sql) |
