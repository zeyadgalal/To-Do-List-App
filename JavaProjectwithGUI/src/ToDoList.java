import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
public class ToDoList {
    private static final List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoList::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Layout setup
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        frame.add(panel);

        // Task display area
        DefaultListModel<String> taskListModel = new DefaultListModel<>();
        JList<String> taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        panel.add(buttonPanel, BorderLayout.SOUTH);

        JButton addTaskButton = new JButton("Add Task");
        JButton markCompletedButton = new JButton("Mark Completed");
        JButton deleteTaskButton = new JButton("Delete Task");
        JButton viewAllTasksButton = new JButton("View All Tasks");
        JButton filterByPriorityButton = new JButton("Filter by Priority");
        JButton exitButton = new JButton("Exit");
        buttonPanel.add(addTaskButton);
        buttonPanel.add(markCompletedButton);
        buttonPanel.add(deleteTaskButton);
        buttonPanel.add(viewAllTasksButton);
        buttonPanel.add(filterByPriorityButton);
        buttonPanel.add(exitButton);

        // Add Task Button Action
        addTaskButton.addActionListener(e -> {
            JTextField titleField = new JTextField(15);
            JTextField descriptionField = new JTextField(15);
            JTextField priorityField = new JTextField(5);
            JTextField dueDateField = new JTextField(10);

            JPanel inputPanel = new JPanel();
            inputPanel.add(new JLabel("Title:"));
            inputPanel.add(titleField);
            inputPanel.add(new JLabel("Description:"));
            inputPanel.add(descriptionField);
            inputPanel.add(new JLabel("Priority (1=High, 2=Medium, 3=Low):"));
            inputPanel.add(priorityField);
            inputPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
            inputPanel.add(dueDateField);

            int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Add Task", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String title = titleField.getText().trim();
                    String description = descriptionField.getText().trim();
                    String priorityStr = priorityField.getText().trim();
                    String dueDate = dueDateField.getText().trim();

                    // Validations
                    if (title.isEmpty() || description.isEmpty() || priorityStr.isEmpty() || dueDate.isEmpty()) {
                        throw new IllegalArgumentException("All fields must be filled.");
                    }

                    if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", dueDate)) {
                        throw new IllegalArgumentException("Date must be in format YYYY-MM-DD.");
                    }

                    if (title.matches("\\d+") || description.matches("\\d+")) {
                        throw new IllegalArgumentException("Title and Description must not contain numbers only.");
                    }

                    int priority = Integer.parseInt(priorityStr);
                    if (priority < 1 || priority > 3) {
                        throw new IllegalArgumentException("Priority must be 1, 2, or 3.");
                    }

                    Task task = new PersonalTask(title, description, priority, dueDate); // Default to PersonalTask
                    tasks.add(task);
                    taskListModel.addElement(task.getDetails());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Task not added.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mark Task as Completed Button Action
        markCompletedButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                tasks.get(selectedIndex).markAsCompleted();
                taskListModel.set(selectedIndex, tasks.get(selectedIndex).getDetails());
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to mark as completed.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Delete Task Button Action
        deleteTaskButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                tasks.remove(selectedIndex);
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a task to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // View All Tasks Button Action
        viewAllTasksButton.addActionListener(e -> {
            StringBuilder allTasks = new StringBuilder("All Tasks:\n\n");
            for (Task task : tasks) {
                allTasks.append(task.getDetails()).append("\n\n");
            }
            JOptionPane.showMessageDialog(frame, allTasks.toString(), "All Tasks", JOptionPane.INFORMATION_MESSAGE);
        });

        // Filter by Priority Button Action
        filterByPriorityButton.addActionListener(e -> {
            String[] options = {"1", "2", "3"};
            String priority = (String) JOptionPane.showInputDialog(frame, "Select Priority to Filter:", "Filter Tasks by Priority",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (priority != null) {
                int selectedPriority = Integer.parseInt(priority);
                List<Task> filteredTasks = tasks.stream()
                        .filter(task -> task.getPriority() == selectedPriority)
                        .collect(Collectors.toList());

                StringBuilder filteredTaskDetails = new StringBuilder("Tasks with Priority " + priority + ":\n\n");
                for (Task task : filteredTasks) {
                    filteredTaskDetails.append(task.getDetails()).append("\n\n");
                }
                JOptionPane.showMessageDialog(frame, filteredTaskDetails.toString(), "Filtered Tasks", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Exit Button Action
        exitButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}