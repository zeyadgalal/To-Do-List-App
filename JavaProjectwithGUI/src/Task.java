abstract class Task {
    private String title;
    private String description;
    private int priority; // 1 = High, 2 = Medium, 3 = Low
    private String dueDate; // Format: YYYY-MM-DD
    private boolean completed;

    // Constructor
    public Task(String title, String description, int priority, String dueDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }

    // Encapsulation: Getters and Setters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public String getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public void markAsCompleted() { this.completed = true; }

    // Abstraction: Abstract method for displaying task details
    public abstract String getDetails();
}