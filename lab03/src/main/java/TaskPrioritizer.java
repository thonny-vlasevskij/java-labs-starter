package edu.course.lab03;
public class TaskPrioritizer {
    private final PriorityPolicy policy;
    public TaskPrioritizer(PriorityPolicy policy) {
        if (policy == null) throw new IllegalArgumentException("policy must not be null");
        this.policy = policy;
    }
    public ProjectTask chooseHigherPriority(ProjectTask first, ProjectTask second) {
        if (policy.calculatePriority(first) >= policy.calculatePriority(second)) return first;
        return second;
    }
}
