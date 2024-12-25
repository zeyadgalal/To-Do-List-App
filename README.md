# To-Do List Application

This is a Java-based To-Do List application with a graphical user interface (GUI) built using Swing. It allows users to manage their tasks efficiently by adding, viewing, filtering, marking as completed, and deleting tasks.

## Features

1. **Add Task**:
   - Users can add tasks by specifying the title, description, priority (1=High, 2=Medium, 3=Low), and due date (YYYY-MM-DD).
   - Validations ensure fields are non-empty, the due date is in the correct format, and title/description are not numeric-only.

2. **View All Tasks**:
   - Displays a list of all tasks, including their details such as title, description, priority, due date, and status (Pending/Completed).

3. **Filter by Priority**:
   - Users can filter tasks by priority levels (High, Medium, or Low).

4. **Mark Task as Completed**:
   - Allows marking a selected task as completed, updating its status in the task list.

5. **Delete Task**:
   - Enables deletion of a selected task from the list.

6. **Exit**:
   - Includes an exit button to close the application with confirmation.

## Validations

- All fields are required when adding a task.
- Due date must follow the format `YYYY-MM-DD`.
- Title and description must contain meaningful text and cannot be numbers only.
- Priority must be 1, 2, or 3.

## Technologies Used

- **Object-Oriented Programming**: Includes encapsulation, inheritance, polymorphism, and abstraction.
- **Java Swing**: For GUI development.

## How to Run the Application

1. **Prerequisites**:
   - Install JDK 8 or higher.
   - Set up a Java development environment (e.g., IntelliJ IDEA, or VS Code with Java extensions).

2. **Steps to Compile and Run**:
   - Clone or download the project files.
   - Open the project in your Java IDE.
   - Compile and run the `ToDoList` class.

3. **Using the Application**:
   - Use the buttons in the interface to add tasks, view tasks, filter by priority, mark tasks as completed, and delete tasks.
   - Exit the application using the "Exit" button.


## Author

This project was developed as part of an Advanced Java Programming course at Port Said University by:
- Zeyad Galal Hebeshy - Developer
- Ibrahim Adel Elkerdawy - Developer
- Nour Mohamed Elsharnouby - Developer
- Mostafa Ahmed Abo Amasha - Developer

