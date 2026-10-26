package edu.course.lab03;
public class ProjectTask {
    private final String id;
    private final String title;
    private final int estimatedHours;
    private final TaskStatus status;

    public ProjectTask(String id, String title, int estimatedHours, TaskStatus status) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("id must not be null or empty");
        if (title == null || title.isEmpty()) throw new IllegalArgumentException("title must not be null or empty");
        if (estimatedHours < 0) throw new IllegalArgumentException("estimatedHours must not be negative");
        if (status == null) throw new IllegalArgumentException("status must not be null");
        this.id = id; this.title = title; this.estimatedHours = estimatedHours; this.status = status;
    }
    public int getEstimatedHours() { return estimatedHours; }
    public TaskStatus getStatus() { return status; }
}
