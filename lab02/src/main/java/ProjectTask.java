public final class ProjectTask {
    private final String id;
    private final String title;
    private TaskStatus status;
    private int estimatedHours;

    public ProjectTask(String id, String title, TaskStatus status, int estimatedHours) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id must not be null or empty");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title must not be null or empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        if (estimatedHours <= 0) {
            throw new IllegalArgumentException("estimatedHours must be positive");
        }

        this.id = id;
        this.title = title;
        this.status = status;
        this.estimatedHours = estimatedHours;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void changeStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
        this.status = status;
    }

    public boolean isCompleted() {
        return status == TaskStatus.DONE;
    }

    public void increaseEstimatedHours(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("hours must be positive");
        }
        estimatedHours += hours;
    }
}
