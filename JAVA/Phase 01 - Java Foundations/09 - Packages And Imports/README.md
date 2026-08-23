# Topic 1.9 — Packages & Imports

## 1. Why Packages Exist
1. **Organization:** Grouping related classes into modular directory namespaces (`com.company.service`, `com.company.model`).
2. **Naming Collision Prevention:** Enables distinct classes to share names safely (`java.util.Date` vs `java.sql.Date`).

---

## 2. Package Rules & Naming Conventions (Effective Java Item 68)
- `package com.kunal.utils;` must be the **first non-comment line** of the source file.
- Folder hierarchy must strictly mirror the package name.
- **Convention:** Reverse Internet domain name in all lowercase (`com.google.common`, `org.apache.commons`). Domains are globally unique, guaranteeing zero global namespace collisions.

---

## 3. The 4 Classic Package & Import Traps

1. **Wildcards (`*`) Do Not Import Subpackages:**
   `import java.util.*;` imports `ArrayList` and `Scanner`, but **NEVER** subpackages like `java.util.concurrent.BlockingQueue`. Packages in Java are not nested in semantics; they are flat namespaces with dot notation.
2. **Import Ambiguity:**
   ```java
   import java.util.*;
   import java.sql.*;
   Date d = new Date(); // ❌ Compile Error: reference to Date is ambiguous
   ```
   - **Fix:** Add a specific single-type import (`import java.util.Date;`).
3. **The Automatic Import:** `java.lang.*` is automatically imported into every Java source file (`String`, `System`, `Math`, `Object`, `Integer`).
4. **Visibility Across Packages:** Non-`public` classes cannot cross package boundaries.
