# HR Database Schema (PostgreSQL)

This schema represents the database environment used across all SQL exercises in this repository.

---

## 📊 Database Tables

The database consists of **4 active tables**:

```
┌─────────────────┐       ┌─────────────────┐       ┌─────────────────┐
│    locations    │       │   departments   │       │    employees    │
├─────────────────┤       ├─────────────────┤       ├─────────────────┤
│ LOCATION_ID (PK)│◄──┐   │ DEPARTMENT_ID(PK│◄──┐   │ EMPLOYEE_ID (PK)│
│ STREET_ADDRESS  │   └───┤ LOCATION_ID     │   └───┤ DEPARTMENT_ID   │
│ POSTAL_CODE     │       │ DEPARTMENT_NAME │       │ FIRST_NAME      │
│ CITY            │       │ MANAGER_ID      │       │ LAST_NAME       │
│ STATE_PROVINCE  │       └─────────────────┘       │ EMAIL           │
│ COUNTRY_ID      │                                 │ PHONE_NUMBER    │
└─────────────────┘                                 │ HIRE_DATE       │
                                                    │ JOB_ID          │
                                                    │ SALARY          │
                                                    │ COMMISSION_PCT  │
┌─────────────────┐                                 │ MANAGER_ID      │
│   job_history   │                                 └────────┬────────┘
├─────────────────┤                                          │
│ EMPLOYEE_ID (PK)│◄─────────────────────────────────────────┘
│ START_DATE  (PK)│
│ END_DATE        │
│ JOB_ID          │
│ DEPARTMENT_ID   │
└─────────────────┘
```

> **Note on Schema Constraints:**
> - `jobs` and `countries` tables are **not present** in this environment.
> - Job titles/roles are directly represented by `employees.job_id` and `job_history.job_id`.
> - Country identifiers are retrieved directly via `locations.country_id`.
> - Departments without active managers hold `MANAGER_ID = 0`.
> - Employees without assigned departments hold `DEPARTMENT_ID = 0`.

---

## 🛠️ Setup Script
Run [`setup.sql`](./setup.sql) in PostgreSQL to initialize and populate the tables.
