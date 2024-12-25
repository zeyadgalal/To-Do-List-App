class PersonalTask extends Task {
    public PersonalTask(String title, String description, int priority, String dueDate) {
        super(title, description, priority, dueDate);
    }

    @Override
    public String getDetails() {
        return "[Personal Task] Title: " + getTitle() + ", Description: " + getDescription() + ", Priority: " +
                (getPriority() == 1 ? "High" : getPriority() == 2 ? "Medium" : "Low") + ", Due Date: " + getDueDate() +
                ", Status: " + (isCompleted() ? "Completed" : "Pending");
    }
}