public final class ProjectTask {
    private final String id, title, category;
    private final int estimatedHours;
    private final TaskStatus status;
    public ProjectTask(String id, String title, String category, int estimatedHours, TaskStatus status) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id must not be empty");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title must not be empty");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("category must not be empty");
        if (estimatedHours < 0) throw new IllegalArgumentException("estimatedHours must not be negative");
        if (status == null) throw new IllegalArgumentException("status must not be null");
        this.id=id; this.title=title; this.category=category; this.estimatedHours=estimatedHours; this.status=status;
    }
    public String getId(){ return id; }
    public String getTitle(){ return title; }
    public String getCategory(){ return category; }
    public int getEstimatedHours(){ return estimatedHours; }
    public TaskStatus getStatus(){ return status; }
}
