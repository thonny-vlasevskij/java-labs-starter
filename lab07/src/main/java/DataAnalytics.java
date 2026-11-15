package edu.course.lab07;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataAnalytics {

    public List<ProjectTask> filterByStatus(List<ProjectTask> tasks, TaskStatus status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public List<String> sortedCategories(List<ProjectTask> tasks) {
        return tasks.stream()
                .map(task -> task.getCategory().trim().toLowerCase())
                .distinct()
                .sorted()
                .toList();
    }

    public Map<String, Long> countByCategory(List<ProjectTask> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(
                        task -> task.getCategory().trim().toLowerCase(),
                        Collectors.counting()
                ));
    }

    public Optional<ProjectTask> findMostTimeConsuming(List<ProjectTask> tasks) {
        return tasks.stream()
                .max(Comparator.comparingInt(ProjectTask::getEstimatedHours));
    }

    public List<ProjectTask> sortedByEffortThenTitle(List<ProjectTask> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparingInt(ProjectTask::getEstimatedHours)
                        .thenComparing(ProjectTask::getTitle))
                .toList();
    }
}
