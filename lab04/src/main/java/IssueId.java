public final class IssueId {
    private final String projectCode;
    private final int number;
    public IssueId(String projectCode, int number) {
        if (projectCode == null || projectCode.isEmpty()) throw new IllegalArgumentException("projectCode must not be null or empty");
        if (number <= 0) throw new IllegalArgumentException("number must be positive");
        this.projectCode = projectCode; this.number = number;
    }
    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof IssueId)) return false;
        IssueId id = (IssueId) other;
        return number == id.number && projectCode.equals(id.projectCode);
    }
    @Override public int hashCode() { return 31 * projectCode.hashCode() + number; }
    @Override public String toString() { return projectCode + "-" + number; }
}
