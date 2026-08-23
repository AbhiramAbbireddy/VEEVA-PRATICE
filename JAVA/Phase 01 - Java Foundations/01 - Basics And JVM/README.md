# Topic 1.1 — What is Java? JDK, JRE, JVM & Execution Flow

## 1. Intuition: The Universal Translator
Imagine writing a book. If you translate it into 50 human languages individually, every update is painful. Instead, you write it in **one intermediate format** (Bytecode) and provide each reader a personal interpreter on their device (**JVM**).
- Java slogan: **"Write Once, Run Anywhere" (WORA)**.
- You compile **once** into Bytecode (`.class`), and the platform-specific JVM translates it to machine code at runtime.

---

## 2. JDK vs JRE vs JVM

```
┌─────────────────────────────────────────┐
│  JDK (Java Development Kit)             │
│  Contains: javac, debugger, javadoc...  │
│   ┌─────────────────────────────────┐   │
│   │  JRE (Java Runtime Environment) │   │
│   │  Contains: libraries + JVM      │   │
│   │   ┌─────────────────────────┐   │   │
│   │   │  JVM (Java Virtual      │   │   │
│   │   │  Machine)               │   │   │
│   │   │  Executes Bytecode      │   │   │
│   │   └─────────────────────────┘   │   │
│   └─────────────────────────────────┘   │
└─────────────────────────────────────────┘
```

| Component | Role | Analogy |
|:---|:---|:---|
| **JVM** | Executes bytecode into native machine instructions | The **Engine** |
| **JRE** | JVM + Standard Class Libraries (`java.lang`, `java.util`) | The **Car** (Engine + Parts) |
| **JDK** | JRE + Development Tools (Compiler `javac`, Debugger, Tools) | The **Factory** that builds cars |

---

## 3. The Compilation & Execution Flow

```
YourCode.java ──► javac (Compiler) ──► YourCode.class (BYTECODE) ──► java (JVM) ──► Native Machine Code
```

1. **Compile Time:** `javac Hello.java` produces `Hello.class`. Catches syntax and type mismatches.
2. **Run Time:** `java Hello` (class name, never `Hello.class`). The JVM loads bytecode and executes instructions.

---

## 4. Key Interview Questions & Traps

> **Q: Is Java 100% platform-independent?**
> **A:** Java **bytecode** is platform-independent, but the **JVM is platform-dependent**. Each operating system (Windows, Linux, macOS) requires its own specifically built JVM to translate bytecode into that OS's native machine instructions.

> **Q: What is the difference between `javac Demo.java` and `java Demo`?**
> **A:** `javac` takes **source file names**; `java` takes **class names**. Passing `java Demo.class` causes the JVM to search for a class literally named `Demo.class`, throwing `ClassNotFoundException` / `Could not find or load main class`.
