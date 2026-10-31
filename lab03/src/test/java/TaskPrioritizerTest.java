package edu.course.lab03;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TaskPrioritizerTest {
    private ProjectTask task(String id, int hours, TaskStatus status) {
        return new ProjectTask(id, "Task " + id, hours, status);
    }
    @Test void effortPolicyChoosesMoreHours() {
        ProjectTask a = task("1", 10, TaskStatus.TODO), b = task("2", 5, TaskStatus.TODO);
        assertSame(a, new TaskPrioritizer(new EffortPriorityPolicy()).chooseHigherPriority(a, b));
    }
    @Test void statusPolicyChoosesHigherStatus() {
        ProjectTask a = task("1", 5, TaskStatus.TODO), b = task("2", 5, TaskStatus.DONE);
        assertSame(b, new TaskPrioritizer(new StatusPriorityPolicy()).chooseHigherPriority(a, b));
    }
}
