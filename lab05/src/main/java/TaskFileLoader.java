import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class TaskFileLoader {
    private static final String HEADER = "id;title;category;estimatedHours;status";
    public LoadResult load(Path path) {
        final List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new DataLoadingException("Cannot read " + path, exception);
        }
        if (lines.isEmpty() || !HEADER.equals(lines.get(0).trim())) {
            throw new IllegalArgumentException("Invalid file header");
        }
        List<ProjectTask> tasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();
        for (int i=1; i<lines.size(); i++) {
            String line=lines.get(i);
            if (line.isBlank()) continue;
            String[] f=line.split(";", -1);
            if (f.length != 5) {
                errors.add(new ValidationError(i+1, "row", "Expected 5 fields, got " + f.length));
                continue;
            }
            String[] names={"id","title","category","estimatedHours","status"};
            for (int j=0; j<f.length; j++) f[j]=f[j].trim();
            boolean empty=false;
            for (int j=0; j<f.length; j++) if (f[j].isEmpty()) {
                errors.add(new ValidationError(i+1, names[j], names[j] + " must not be empty"));
                empty=true; break;
            }
            if (empty) continue;
            int hours;
            try { hours=Integer.parseInt(f[3]); }
            catch (NumberFormatException ex) {
                errors.add(new ValidationError(i+1, "estimatedHours", "Not a valid integer: " + f[3]));
                continue;
            }
            if (hours < 0) {
                errors.add(new ValidationError(i+1, "estimatedHours", "estimatedHours must not be negative"));
                continue;
            }
            TaskStatus status;
            try { status=TaskStatus.valueOf(f[4]); }
            catch (IllegalArgumentException ex) {
                errors.add(new ValidationError(i+1, "status", "Unknown status: " + f[4]));
                continue;
            }
            try { tasks.add(new ProjectTask(f[0], f[1], f[2], hours, status)); }
            catch (IllegalArgumentException ex) {
                errors.add(new ValidationError(i+1, "row", ex.getMessage()));
            }
        }
        return new LoadResult(tasks, errors);
    }
}
