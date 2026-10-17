import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjectTaskTest {
    @Test
    void createsValidTask() {
        ProjectTask task = new ProjectTask("TASK-1", "Implement login", TaskStatus.TODO, 8);

        assertEquals("TASK-1", task.getId());
        assertEquals("Implement login", task.getTitle());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(8, task.getEstimatedHours());
    }

    @Test
    void constructorRejectsNullOrEmptyId() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask(null, "Task", TaskStatus.TODO, 8));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("", "Task", TaskStatus.TODO, 8));
    }

    @Test
    void constructorRejectsNullOrEmptyTitle() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", null, TaskStatus.TODO, 8));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "", TaskStatus.TODO, 8));
    }

    @Test
    void constructorRejectsNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "Task", null, 8));
    }

    @Test
    void constructorRejectsNonPositiveEstimatedHours() {
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "Task", TaskStatus.TODO, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ProjectTask("TASK-1", "Task", TaskStatus.TODO, -1));
    }

    @Test
    void changeStatusChangesStatusWithoutSetter() {
        ProjectTask task = new ProjectTask("TASK-1", "Task", TaskStatus.TODO, 8);

        task.changeStatus(TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    void changeStatusRejectsNull() {
        ProjectTask task = new ProjectTask("TASK-1", "Task", TaskStatus.TODO, 8);

        assertThrows(IllegalArgumentException.class, () -> task.changeStatus(null));
    }

    @Test
    void isCompletedReturnsTrueOnlyForDoneStatus() {
        ProjectTask task = new ProjectTask("TASK-1", "Task", TaskStatus.TODO, 8);

        assertFalse(task.isCompleted());
        task.changeStatus(TaskStatus.DONE);
        assertTrue(task.isCompleted());
    }

    @Test
    void increaseEstimatedHoursAddsPositiveHours() {
        ProjectTask task = new ProjectTask("TASK-1", "Task", TaskStatus.TODO, 8);

        task.increaseEstimatedHours(4);

        assertEquals(12, task.getEstimatedHours());
    }

    @Test
    void increaseEstimatedHoursRejectsNonPositiveHours() {
        ProjectTask task = new ProjectTask("TASK-1", "Task", TaskStatus.TODO, 8);

        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimatedHours(0));
        assertThrows(IllegalArgumentException.class, () -> task.increaseEstimatedHours(-2));
    }
}
