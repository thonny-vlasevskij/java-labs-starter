import java.util.HashMap;
import java.util.Map;
public class IssueRegistry {
    private final Map<IssueId, ProjectTask> tasks = new HashMap<>();
    public void put(IssueId id, ProjectTask task) { tasks.put(id, task); }
    public ProjectTask get(IssueId id) { return tasks.get(id); }
    public ProjectTask remove(IssueId id) { return tasks.remove(id); }
    public boolean contains(IssueId id) { return tasks.containsKey(id); }
    public int size() { return tasks.size(); }
}
