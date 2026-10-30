import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
class BookTest {
 @Test void equalBooksFindValueInHashMap(){ Map<Book,String> books=new HashMap<>(); books.put(new Book("123","First"),"value"); assertEquals("value",books.get(new Book("123","Second"))); }
 @Test void differentIsbnMeansDifferentBooks(){ assertNotEquals(new Book("123","Book"),new Book("456","Book")); }
 @Test void nullAndOtherTypeAreNotEqual(){ Book book=new Book("123","Book"); assertNotEquals(book,null); assertNotEquals(book,"123"); }
}
