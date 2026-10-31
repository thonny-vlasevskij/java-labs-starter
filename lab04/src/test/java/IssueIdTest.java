import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class IssueIdTest {
 @Test void equalIdsHaveEqualHashCodes(){ IssueId a=new IssueId("PROJ",1), b=new IssueId("PROJ",1); assertEquals(a,b); assertEquals(a.hashCode(),b.hashCode()); }
 @Test void differentIdsCanHaveSameHashCode(){ IssueId a=new IssueId("Aa",1), b=new IssueId("BB",1); assertNotEquals(a,b); assertEquals(a.hashCode(),b.hashCode()); }
 @Test void nullAndOtherTypeAreNotEqual(){ IssueId id=new IssueId("PROJ",1); assertFalse(id.equals(null)); assertFalse(id.equals("PROJ-1")); }
}
