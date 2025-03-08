# Library Management System (LMS) Refactoring

**Submitted to:** Ma’am Hareem Haider  
**Course:** Software Design & Architecture  
**Submitted by:**  
- **Nagarash Fateh**  
  - **Sap Id:** 44815  
  - **Department:** BSSE  
  - **Email:** [44815@students.riphah.edu.pk](mailto:44815@students.riphah.edu.pk)  
- **Manahil Habib**  
  - **Sap Id:** 47876  
  - **Department:** BSSE  
  - **Email:** [47876@students.riphah.edu.pk](mailto:47876@students.riphah.edu.pk)  
- **Wafi Wahid**  
  - **Sap Id:** 47322  
  - **Department:** BSSE  
  - **Email:** [47322@students.riphah.edu.pk](mailto:47322@students.riphah.edu.pk)  
- **Jaweriya Khan**  
  - **Sap Id:** 46549  
  - **Department:** BSSE  
  - **Email:** [46549@students.riphah.edu.pk](mailto:46549@students.riphah.edu.pk)  
- **Maria Kiran**  
  - **Sap Id:** 44565  
  - **Department:** BSSE  
  - **Email:** [44565@students.riphah.edu.pk](mailto:44565@students.riphah.edu.pk)  

**Department of Computing**  
**Date:** 5th March, 2025  

## Table of Contents
1. [Introduction](#introduction)
2. [Project Selection](#project-selection)
3. [Analysis: Identifying SOLID Violations](#analysis-identifying-solid-violations)
4. [Refactoring Approach: Applying SOLID Principles](#refactoring-approach-applying-solid-principles)
    - [4.1 Single Responsibility Principle (SRP)](#41-single-responsibility-principle-srp)
    - [4.2 Open/Closed Principle (OCP)](#42-openclosed-principle-ocp)
    - [4.3 Liskov Substitution Principle (LSP)](#43-liskov-substitution-principle-lsp)
    - [4.4 Interface Segregation Principle (ISP)](#44-interface-segregation-principle-isp)
    - [4.5 Dependency Inversion Principle (DIP)](#45-dependency-inversion-principle-dip)
5. [Justification: Why the Refactored Design is Better](#justification-why-the-refactored-design-is-better)

## Introduction
The SOLID principles are a set of five design principles that enhance software maintainability, scalability, and flexibility. These principles are:

- **Single Responsibility Principle (SRP):** A class should have only one reason to change.
- **Open/Closed Principle (OCP):** A class should be open for extension but closed for modification.
- **Liskov Substitution Principle (LSP):** Subtypes must be substitutable for their base types.
- **Interface Segregation Principle (ISP):** A class should not be forced to depend on methods it does not use.
- **Dependency Inversion Principle (DIP):** High-level modules should depend on abstractions, not concrete implementations.

Applying these principles ensures improved modularity, readability, and ease of modification.

## Project Selection
The selected project for refactoring is the Library Management System (LMS) hosted on GitHub: **Library Management System - JAVA**. This project was chosen due to its:

- **Complexity:** The system includes book management, borrowing, and staff interactions.
- **Code Issues:** The original implementation had several violations of SOLID principles, making it an ideal candidate for refactoring.
- **Practical Use:** Library systems are widely used, making this refactoring valuable for real-world applications.

## Analysis: Identifying SOLID Violations
Before refactoring, the original LMS code contained multiple design flaws:

- **SRP Violation:** Classes like `Library.java` and `Loan.java` handled multiple responsibilities, making maintenance difficult.
- **OCP Violation:** Adding new features required modifying existing classes instead of extending them.
- **LSP Violation:** Certain subclasses, such as `Clerk.java`, introduced behaviors that made them incompatible with `Staff.java`.
- **ISP Violation:** Large interfaces forced classes to implement unnecessary methods.
- **DIP Violation:** High-level modules were directly dependent on low-level implementations, limiting flexibility.

## Refactoring Approach: Applying SOLID Principles
### 4.1 Single Responsibility Principle (SRP)
**Changes Made:**
- Refactored monolithic classes: `Library.java` was broken down into `LibraryService.java`, `LibraryUI.java`, and `LibraryDAO.java`.
- Dedicated classes for specific actions:
  - `BookPrinter.java` to handle book printing.
  - `BorrowService.java` to manage borrowing operations.
  - `LoanRenewalService.java` for loan renewal.

**Impact:** Increased maintainability and reduced complexity by ensuring each class has a single responsibility.

### 4.2 Open/Closed Principle (OCP)
**Modifications Included:**
- Introduced abstractions:
  - `BookOperation` allows book-related actions to be extended without modifying `BookService.java`.
  - `FineStrategy` enables different fine calculation methods (e.g., `FixedRateFineStrategy`, `ProgressiveFineStrategy`).

**Impact:** New functionalities can be added without altering existing classes, reducing the risk of breaking the system.

### 4.3 Liskov Substitution Principle (LSP)
**Key Refactoring Included:**
- Unified behavior across subtypes:
  - `OfficeAssignedStaff.java` was introduced to standardize office assignments for `Librarian.java` and `Staff.java`.
  - `FineCalculator.java` now supports interchangeable fine strategies.

**Impact:** Ensured subclasses can replace their base class without introducing inconsistencies.

### 4.4 Interface Segregation Principle (ISP)
**Refactoring Changes:**
- Split large interfaces into smaller ones:
  - `BookAction` was divided into `Issuable`, `Returnable`, and `Reservable`.
  - `PersonAction` was split into `AddressUpdateAction`, `PhoneUpdateAction`, and `NameUpdateAction`.

**Impact:** Eliminated unnecessary dependencies, making implementations more modular and adaptable.

### 4.5 Dependency Inversion Principle (DIP)
**Changes Included:**
- Replaced direct dependencies with abstractions:
  - `LibraryService.java` depends on `LibraryInterface`, making it flexible for different implementations.
  - `BookService.java` now works with `IHoldRequestRepository` rather than a concrete repository class.

**Impact:** Reduced tight coupling, improving flexibility and ease of modification.

## Justification: Why the Refactored Design is Better
The refactored LMS is superior to the original in multiple ways:

- **Better Maintainability:** Smaller, well-defined classes make the code easier to read and modify.
- **Scalability:** New features can be added without modifying core classes, reducing system fragility.
- **Improved Testability:** Individual components can be unit-tested independently, increasing reliability.
- **Enhanced Flexibility:** Dependency inversion allows different implementations without altering the main system.

