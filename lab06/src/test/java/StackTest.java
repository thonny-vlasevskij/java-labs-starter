import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
class StackTest {
    @Test void newStackIsEmpty() { Stack<String> s = new Stack<>(); assertTrue(s.isEmpty()); assertEquals(0, s.size()); }
    @Test void pushAndPeek() { Stack<String> s = new Stack<>(); s.push("first"); assertEquals("first", s.peek()); assertEquals(1, s.size()); }
    @Test void popReturnsLastAdded() { Stack<Integer> s = new Stack<>(); s.push(10); s.push(20); assertEquals(20, s.pop()); assertEquals(10, s.pop()); assertTrue(s.isEmpty()); }
    @Test void emptyPopAndPeekThrow() { Stack<String> s = new Stack<>(); assertThrows(NoSuchElementException.class, s::pop); assertThrows(NoSuchElementException.class, s::peek); }
    @Test void nullIsRejected() { Stack<String> s = new Stack<>(); assertThrows(IllegalArgumentException.class, () -> s.push(null)); }
}
