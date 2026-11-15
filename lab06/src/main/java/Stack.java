import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
public final class Stack<T> {
    private final List<T> values = new ArrayList<>();
    public void push(T value) {
        if (value == null) throw new IllegalArgumentException("null is not allowed");
        values.add(value);
    }
    public T pop() {
        if (values.isEmpty()) throw new NoSuchElementException("stack is empty");
        return values.remove(values.size() - 1);
    }
    public T peek() {
        if (values.isEmpty()) throw new NoSuchElementException("stack is empty");
        return values.get(values.size() - 1);
    }
    public int size() { return values.size(); }
    public boolean isEmpty() { return values.isEmpty(); }
}
