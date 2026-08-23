# Learning Repository Maintenance Rules

## Repository Location
`d:\VEEVA-PRATICE`

## Architecture (DO NOT MODIFY)

```
Repository
│
├── BSF/
│   └── Root Analysis/
│       └── <Problem Name>/
│           ├── README.md
│           └── Solution.java
│
├── SQL/
│   ├── Problems/
│   │   └── <Problem Name>/
│   │       ├── README.md
│   │       └── solution.sql
│   │
│   └── Concepts/
│       └── <Concept Name>/
│           └── README.md
│
├── LLD/
│   └── <Topic Name>/
│       ├── README.md
│       └── <Sub-topic folders with README.md each>
│
├── DSA/
├── JAVA/
└── (other folders the user may add)
```

## BSF Folder Rules
- Used for documenting development problems with root cause analysis.
- Each problem goes inside `BSF/Root Analysis/<Problem Name>/`.
- README.md must contain: Problem Description, Root Cause Analysis, Why the issue happened, Investigation Process, Fix Applied, Lessons Learned, Prevention Tips.
- Solution.java contains the final working fix.
- Only add extra files if the user explicitly asks.

## SQL Folder Rules

### SQL/Problems/
- Each solved SQL problem gets its own folder: `SQL/Problems/<Problem Name>/`.
- README.md must contain: Problem Statement, Approach, Logic, Time Complexity (if applicable), Important SQL Concepts Used, Alternative Approaches (if any).
- solution.sql contains only the final SQL solution.

### SQL/Concepts/
- Each concept gets its own folder: `SQL/Concepts/<Concept Name>/`.
- README.md must contain: Definition, Why it is used, Syntax, Examples, Best Practices, Common Mistakes.

## LLD Folder Rules
- One design pattern or design principle per topic folder.
- Top-level README.md for overviews.
- Sub-folders for individual principles/patterns with their own README.md.
- Each sub-topic README.md must contain: Definition, Why it exists, Real-world analogy, Java example, Benefits, Drawbacks, Best Practices, Common Interview Questions.

## Daily Workflow
1. Identify which section the new learning belongs to.
2. Create the appropriate folder if it doesn't exist.
3. Follow the folder structure exactly.
4. Generate README files first, then code files.
5. Keep naming consistent and professional.
6. Do not modify existing folders unless explicitly asked.

## Documentation Standards
- Well structured, beginner friendly, Markdown formatted.
- Easy to revise later.
- Include examples and best practices wherever applicable.

## Naming Conventions
- **Folders**: Pascal Case (e.g., Root Analysis, Window Functions, Factory Pattern, Top 2 Salaries).
- **Files**: README.md, Solution.java, solution.sql.

## Important Rules
- NEVER change the repository architecture without explicit instruction.
- NEVER merge unrelated topics into the same folder.
- Keep each topic isolated.
- Always create documentation before code.
- Code must be clean, production-quality, and well commented.
- This is a long-term personal knowledge base — treat it accordingly.
- If the user provides only a problem, infer the correct location and generate complete folder contents.
- If asked to update an existing topic, modify only that topic.
