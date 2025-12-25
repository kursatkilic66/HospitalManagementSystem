# Hospital Management System

A comprehensive console-based application designed to manage hospital operations, patient flows, and medical hierarchies. This project demonstrates advanced knowledge of **Data Structures and Algorithms** by implementing custom versions of fundamental structures (like Linked Lists, Trees, and Hash Maps) instead of relying solely on built-in Java collections.

##  Project Overview

The **Hospital Management System** automates key hospital processes such as:
* **Patient Registration:** Handling both standard clinic appointments and critical emergency cases.
* **Triage System:** Prioritizing emergency patients based on the severity of their condition.
* **Hospital Organization:** Visualizing the hierarchy of departments and doctors.
* **Data Management:** Efficiently storing, searching, and managing patient and doctor records.

##  Key Features

* **Emergency & Normal Flows:** Separates standard patients (sorted by name) from emergency patients (sorted by priority level 1-10).
* **Custom Data Structures:** Built from scratch to demonstrate low-level memory management and algorithmic logic.
* **Undo Mechanism:** Capable of reversing the last patient registration or operation using a Stack.
* **Efficient Searching:** Uses Hash Tables for O(1) access to doctor and patient records by ID.
* **Hierarchical View:** Displays the entire hospital structure (Departments -> Doctors) using a General Tree.

##  Data Structures Implemented

This project relies on the following custom implementations:

### Linear Structures
* **`Node<T>`**: The fundamental atom of the system. Acts as a generic container holding data and pointers, enabling dynamic memory management.
* **`MyLinkedList`**: A dynamic linear structure used as a backbone for stacks, queues, and tree children lists, avoiding fixed-size array limitations.
* **`MyStack` (LIFO)**: Powers the **Undo** functionality. It stores recent transactions to allow quick reversal of user errors.
* **`MyQueue` (FIFO)**: Manages individual waiting lines for every doctor, ensuring patients are treated in the order they arrived.
* **`MyPriorityQueue`**: Specifically designed for the **Emergency Room (ER)**. It orders `ERPatient` objects by medical urgency (severity level) rather than arrival time.

### Non-Linear & Advanced Structures
* **`MyBST` (Binary Search Tree)**: Acts as the main **Patient Archive**. It keeps records sorted alphabetically, allowing for efficient searching and listing.
* **`MyGeneralTree`**: Represents the **Hospital Hierarchy**. Unlike a binary tree, nodes here can have unlimited children (e.g., a department having multiple doctors), enabling a realistic organizational map.
* **`MyHashTable`**: Used for high-speed retrieval of Doctors and Patients by their unique IDs. It implements **chaining** (array of linked lists) to handle collisions.

##  How to Run

1.  **Clone the Repository**
    ```bash
    git clone [https://github.com/yourusername/HospitalManagementSystem.git](https://github.com/yourusername/HospitalManagementSystem.git)
    ```
2.  **Compile the Project**
    Navigate to the project directory and compile the Java files.
    ```bash
    javac *.java
    ```
3.  **Run the Application**
    Start the main application class (Entry point).
    ```bash
    java HospitalApp
    ```

##  Usage / Menu Options

Upon running the application (`HospitalApp`), you will be presented with the following options:

1.  **Normal Patient Register:** Register a patient to a specific clinic (ENT, Internal Medicine, Psychology) and assign them to a doctor.
2.  **EMERGENCY Patient Register:** Register a critical patient. You must assign a **Priority Level (1-10)**, where 10 is the most urgent.
3.  **View The Queues:** Display current waiting lines for the Emergency Service and all individual doctors.
4.  **Hospital Hierarchy:** Print the full organizational tree of the hospital.
5.  **Undo The Last Process:** Mistake? Use this to remove the last registered patient from all records.
6.  **Search Doctor By ID:** Instantly find doctor details using their unique ID.
7.  **Search Patient By ID:** Locate a registered patient's details and status.

##  Class Roles

* **`HospitalApp`**: The User Interface (CLI) that handles input/output and menu navigation.
* **`HospitalSystem`**: The main controller "glue" that orchestrates interactions between the different data structures.
* **`Patient` / `ERPatient`**: Data models representing patients. `ERPatient` wraps a patient with a priority level for the emergency queue.
* **`Doctor`**: Represents medical staff and holds a private `MyQueue` for their specific patients.
