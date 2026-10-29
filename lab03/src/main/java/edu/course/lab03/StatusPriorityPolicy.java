package edu.course.lab03;
public class StatusPriorityPolicy implements PriorityPolicy {
    @Override public int calculatePriority(ProjectTask task) {
        if (task.getStatus() == TaskStatus.DONE) return 3;
        if (task.getStatus() == TaskStatus.IN_PROGRESS) return 2;
        return 1;
    }
}
