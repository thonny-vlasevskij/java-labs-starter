import java.util.Objects;
public final class Book {
    private final String isbn;
    private final String title;
    public Book(String isbn, String title) {
        if (isbn == null || isbn.isEmpty()) throw new IllegalArgumentException("isbn must not be null or empty");
        if (title == null || title.isEmpty()) throw new IllegalArgumentException("title must not be null or empty");
        this.isbn = isbn; this.title = title;
    }
    @Override public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Book)) return false;
        Book book = (Book) other;
        return isbn.equals(book.isbn);
    }
    @Override public int hashCode() { return Objects.hash(isbn); }
    @Override public String toString() { return "Book{isbn='" + isbn + "', title='" + title + "'}"; }
}
