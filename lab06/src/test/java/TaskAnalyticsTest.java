import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
class TaskAnalyticsTest {
    private final TaskAnalytics analytics = new TaskAnalytics();
    private ProjectTask task(String id, String category, int hours, TaskStatus status) {
        return new ProjectTask(id, "Task " + id, category, hours, status);
    }
    @Test void countsTasksByStatus() {
        List<ProjectTask> tasks = List.of(task("T-1","backend",5,TaskStatus.TODO), task("T-2","frontend",3,TaskStatus.DONE), task("T-3","backend",8,TaskStatus.TODO));
        Map<TaskStatus,Integer> counts = analytics.countByStatus(tasks);
        assertEquals(2, counts.get(TaskStatus.TODO)); assertEquals(1, counts.get(TaskStatus.DONE));
    }
    @Test void findsUniqueCategories() {
        List<ProjectTask> tasks = List.of(task("T-1","backend",5,TaskStatus.TODO), task("T-2","frontend",3,TaskStatus.DONE), task("T-3","backend",8,TaskStatus.TODO));
        Set<String> categories = analytics.uniqueCategories(tasks);
        assertEquals(Set.of("backend","frontend"), categories);
    }
    @Test void sortsByHoursThenIdAndKeepsOriginalUnchanged() {
        List<ProjectTask> tasks = new ArrayList<>(List.of(task("T-3","backend",5,TaskStatus.TODO), task("T-2","frontend",2,TaskStatus.DONE), task("T-1","backend",5,TaskStatus.IN_PROGRESS)));
        List<ProjectTask> sorted = analytics.sortedByEffortAndId(tasks);
        assertEquals("T-2", sorted.get(0).getId());
        assertEquals("T-1", sorted.get(1).getId());
        assertEquals("T-3", sorted.get(2).getId());
        assertEquals("T-3", tasks.get(0).getId());
    }
}
