import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
public final class TaskAnalytics {
    public Map<TaskStatus, Integer> countByStatus(List<ProjectTask> tasks) {
        Map<TaskStatus, Integer> counts = new HashMap<>();
        for (ProjectTask task : tasks) {
            TaskStatus status = task.getStatus();
            counts.put(status, counts.getOrDefault(status, 0) + 1);
        }
        return counts;
    }
    public Set<String> uniqueCategories(List<ProjectTask> tasks) {
        Set<String> categories = new HashSet<>();
        for (ProjectTask task : tasks) categories.add(task.getCategory());
        return categories;
    }
    public List<ProjectTask> sortedByEffortAndId(List<ProjectTask> tasks) {
        List<ProjectTask> sorted = new ArrayList<>(tasks);
        sorted.sort(Comparator.comparingInt(ProjectTask::getEstimatedHours).thenComparing(ProjectTask::getId));
        return sorted;
    }
}
