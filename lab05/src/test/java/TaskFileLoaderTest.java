import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskFileLoaderTest {
    @TempDir Path tempDir;
    private final TaskFileLoader loader=new TaskFileLoader();
    private static final String HEADER="id;title;category;estimatedHours;status";
    private Path file(String name, String... lines) throws IOException {
        Path p=tempDir.resolve(name); Files.write(p, List.of(lines), StandardCharsets.UTF_8); return p;
    }
    @Test void loadsValidFile() throws IOException {
        LoadResult r=loader.load(file("ok.txt", HEADER, "T-101;Prepare release;backend;8;READY"));
        assertEquals(1,r.getTasks().size()); assertTrue(r.getErrors().isEmpty());
        assertEquals("T-101",r.getTasks().get(0).getId());
    }
    @Test void readsUnicode() throws IOException {
        LoadResult r=loader.load(file("unicode.txt", HEADER, "T-102;Подготовить релиз;сервер;3;READY"));
        assertEquals("Подготовить релиз",r.getTasks().get(0).getTitle());
    }
    @Test void reportsEmptyField() throws IOException {
        LoadResult r=loader.load(file("empty.txt", HEADER, "T-103;;backend;4;READY"));
        assertEquals("title",r.getErrors().get(0).getField());
    }
    @Test void reportsBadNumber() throws IOException {
        LoadResult r=loader.load(file("bad-number.txt", HEADER, "T-104;Prepare;backend;many;READY"));
        assertEquals("estimatedHours",r.getErrors().get(0).getField());
    }
    @Test void reportsUnknownStatus() throws IOException {
        LoadResult r=loader.load(file("status.txt", HEADER, "T-105;Prepare;backend;4;UNKNOWN"));
        assertEquals("status",r.getErrors().get(0).getField());
    }
    @Test void reportsWrongColumnCount() throws IOException {
        LoadResult r=loader.load(file("columns.txt", HEADER, "T-106;Prepare;backend;4"));
        assertEquals("row",r.getErrors().get(0).getField());
    }
    @Test void continuesAfterBadRow() throws IOException {
        LoadResult r=loader.load(file("mixed.txt", HEADER,
            "T-107;First;backend;8;READY", "T-108;Bad;backend;bad;READY", "T-109;Third;frontend;2;DONE"));
        assertEquals(2,r.getTasks().size()); assertEquals(1,r.getErrors().size());
        assertEquals(3,r.getErrors().get(0).getLineNumber());
    }
    @Test void missingFileWrapsOriginalCause() {
        DataLoadingException ex=assertThrows(DataLoadingException.class,
            () -> loader.load(tempDir.resolve("missing.txt")));
        assertNotNull(ex.getCause()); assertTrue(ex.getMessage().contains("Cannot read"));
    }
    @Test void rejectsWrongHeader() throws IOException {
        assertThrows(IllegalArgumentException.class, () -> loader.load(file("header.txt","wrong;header")));
    }
}
