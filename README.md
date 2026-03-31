# College Management System 🎓

## About The Project
This is a robust, console-based Java application built to manage a college's internal ecosystem. The system is designed using core Object-Oriented Programming (OOP) principles and provides an interface to manage departments, various types of lecturers (BA, MA, Doctors, and Professors), and academic committees. 

## Key Features
* **Role-Based Lecturer Management:** Add and manage different hierarchies of lecturers. The system handles specific attributes depending on the degree (e.g., Doctors and Professors have published articles, Professors have degree-granting institutions).
* **Department & Committee Logic:** Assign lecturers to departments and establish academic committees. The system enforces logical rules, such as restricting committee chairmanship exclusively to Doctors or Professors.
* **Data Persistence (Serialization):** The state of the system is not lost when the program closes. Data is automatically saved to and loaded from a local `.dat` file using Java IO Serialization.
* **Custom Exception Handling:** Ensures system stability using custom exception classes (e.g., `ChairmanExistsException`, `DepartmentNotExistsException`) to prevent invalid operations.
* **Advanced Comparisons:** Utilize Java `Comparator` and `Comparable` interfaces to sort and compare lecturers by their number of published articles, or compare departments by their total members/articles.
* **Object Cloning:** Features deep copying functionality to duplicate existing committees seamlessly.

## Built With
* **Language:** Java
* **Paradigms:** Object-Oriented Programming (Polymorphism, Inheritance, Encapsulation)
* **Data Structures:** Java Collections (`ArrayList`)
* **Storage:** Java File I/O & Object Serialization

## How To Run
1. Clone the repository to your local machine.
2. Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, VS Code).
3. Compile and run the `Main.java` file.
4. Interact with the system using the console menu provided.
