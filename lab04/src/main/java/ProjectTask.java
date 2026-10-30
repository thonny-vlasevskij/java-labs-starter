public final class ProjectTask {
    private final String id;
    private final String title;
    public ProjectTask(String id, String title) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("id must not be null or empty");
        if (title == null || title.isEmpty()) throw new IllegalArgumentException("title must not be null or empty");
        this.id = id; this.title = title;
    }
    public String getId() { return id; }
    public String getTitle() { return title; }
}
