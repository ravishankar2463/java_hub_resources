# Level 2: Enums — Hands-On Lab

[![Live Guide](https://img.shields.io/badge/JavaHub-Read_Full_Lesson-10b981?style=for-the-badge&logo=googlechrome&logoColor=white)](https://ravishankar2463.github.io/java_hub/roadmap/level-2/enums)
[![Branch](https://img.shields.io/badge/Git_Branch-topic--level-2--enums-blue?style=for-the-badge&logo=git&logoColor=white)](https://github.com/ravishankar2463/java_hub_resources/tree/topic/level-2/enums)

> **Companion Code Laboratory for [JavaHub](https://ravishankar2463.github.io/java_hub/)**  
> **Level:** Level 2 (Level 2: The Core Master (Part 1): I/O, Exceptions, and Utilities)  
> **Topic:** Enums

---

## 1. Overview & Lab Objective
This branch contains verified, runnable Java 21 code and JUnit 5 test suites demonstrating the architecture and mechanisms discussed in the **[Enums Deep-Dive Guide](https://ravishankar2463.github.io/java_hub/roadmap/level-2/enums)**.

### Key Concepts Covered:
- Runtime memory models, invariants, and execution flow.
- Idiomatic modern Java 21+ patterns and error prevention.
- Automated assertion test suites with AssertJ and JUnit 5.

---

## 2. Project Structure
```
.
├── pom.xml                                        # Maven configuration (Java 21, JUnit 5, AssertJ)
├── README.md                                      # This lab guide
└── src
    ├── main
    │   └── java
    │       └── io
    │           └── javahub
    │               └── level2
    │                   └── enums
    │                       └── EnumsLab.java     # Implementation code
    └── test
        └── java
            └── io
                └── javahub
                    └── level2
                        └── enums
                            └── EnumsLabTest.java # Verified test suite
```

---

## 3. How to Run & Verify

### Execute the automated test suite:
```bash
mvn clean test
```

### Switch to another topic or return to main:
```bash
# Return to the master catalog
git checkout main

# Or switch to any other topic
git checkout topic/level-<N>/<topic-slug>
```
