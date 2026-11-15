package edu.course.lab07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DataAnalyticsTest {

    private final DataAnalytics analytics = new DataAnalytics();

    private final ProjectTask task1 =
            new ProjectTask("1", "Login", "Backend", 5, TaskStatus.TODO);
    private final ProjectTask task2 =
            new ProjectTask("2", "Registration", "Frontend", 3, TaskStatus.IN_PROGRESS);
    private final ProjectTask task3 =
            new ProjectTask("3", "Database", "Backend", 8, TaskStatus.DONE);
    private final ProjectTask task4 =
            new ProjectTask("4", "API", "Testing", 3, TaskStatus.TODO);

    @Test
    void filterByStatusReturnsOnlyMatchingTasks() {
        List<ProjectTask> result = analytics.filterByStatus(
                List.of(task1, task2, task3, task4), TaskStatus.TODO);
        assertEquals(List.of(task1, task4), result);
    }

    @Test
    void filterByStatusReturnsEmptyList() {
        assertTrue(analytics.filterByStatus(List.of(task1), TaskStatus.DONE).isEmpty());
    }

    @Test
    void filterByStatusWorksWithEmptyInput() {
        assertTrue(analytics.filterByStatus(List.of(), TaskStatus.TODO).isEmpty());
    }

    @Test
    void sortedCategoriesReturnsUniqueNormalizedSortedCategories() {
        List<String> result = analytics.sortedCategories(
                List.of(task1, task2, task3, task4));
        assertEquals(List.of("backend", "frontend", "testing"), result);
    }

    @Test
    void countByCategoryCountsSeveralGroups() {
        Map<String, Long> result = analytics.countByCategory(
                List.of(task1, task2, task3, task4));

        assertEquals(2L, result.get("backend"));
        assertEquals(1L, result.get("frontend"));
        assertEquals(1L, result.get("testing"));
        assertEquals(3, result.size());
    }

    @Test
    void countByCategoryWorksWithEmptyInput() {
        assertTrue(analytics.countByCategory(List.of()).isEmpty());
    }

    @Test
    void findMostTimeConsumingReturnsMaximumTask() {
        Optional<ProjectTask> result = analytics.findMostTimeConsuming(
                List.of(task1, task2, task3, task4));

        assertTrue(result.isPresent());
        assertSame(task3, result.orElseThrow());
    }

    @Test
    void findMostTimeConsumingReturnsEmptyOptional() {
        assertTrue(analytics.findMostTimeConsuming(List.of()).isEmpty());
    }

    @Test
    void findMostTimeConsumingWorksWithOneElement() {
        Optional<ProjectTask> result = analytics.findMostTimeConsuming(List.of(task2));
        assertTrue(result.isPresent());
        assertSame(task2, result.orElseThrow());
    }

    @Test
    void sortedByEffortThenTitleUsesBothKeys() {
        ProjectTask alpha = new ProjectTask(
                "5", "Alpha", "Backend", 3, TaskStatus.TODO);
        ProjectTask beta = new ProjectTask(
                "6", "Beta", "Backend", 3, TaskStatus.TODO);

        List<ProjectTask> result = analytics.sortedByEffortThenTitle(
                List.of(task1, beta, task3, alpha));

        assertEquals(List.of(alpha, beta, task1, task3), result);
    }

    @Test
    void analyticsDoesNotChangeInputList() {
        List<ProjectTask> tasks = new ArrayList<>(List.of(task1, task2, task3));
        List<ProjectTask> original = new ArrayList<>(tasks);

        analytics.filterByStatus(tasks, TaskStatus.TODO);
        analytics.sortedCategories(tasks);
        analytics.countByCategory(tasks);
        analytics.findMostTimeConsuming(tasks);
        analytics.sortedByEffortThenTitle(tasks);

        assertEquals(original, tasks);
    }
}
