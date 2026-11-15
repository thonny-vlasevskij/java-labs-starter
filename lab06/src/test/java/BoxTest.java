import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BoxTest {
    @Test void storesString() {
        Box<String> box = new Box<>("hello");
        assertEquals("hello", box.get()); box.set("world"); assertEquals("world", box.get());
    }
    @Test void storesInteger() {
        Box<Integer> box = new Box<>(42);
        assertEquals(42, box.get()); box.set(100); assertEquals(100, box.get());
    }
    // Не скомпилируется: Box<String> textBox = new Box<>(123);
    // Параметр типа предотвращает помещение Integer в Box<String>.
}
