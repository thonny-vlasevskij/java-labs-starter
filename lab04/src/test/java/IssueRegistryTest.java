import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class IssueRegistryTest {
 @Test void equalKeyFindsValue(){ IssueRegistry r=new IssueRegistry(); r.put(new IssueId("PROJ",1),new ProjectTask("T1","Fix bug")); assertEquals("Fix bug",r.get(new IssueId("PROJ",1)).getTitle()); }
 @Test void equalKeyReplacesValue(){ IssueRegistry r=new IssueRegistry(); r.put(new IssueId("PROJ",1),new ProjectTask("T1","Old")); r.put(new IssueId("PROJ",1),new ProjectTask("T2","New")); assertEquals(1,r.size()); assertEquals("New",r.get(new IssueId("PROJ",1)).getTitle()); }
 @Test void collisionKeepsTwoDifferentValues(){ IssueRegistry r=new IssueRegistry(); IssueId a=new IssueId("Aa",1), b=new IssueId("BB",1); r.put(a,new ProjectTask("1","First")); r.put(b,new ProjectTask("2","Second")); assertEquals(2,r.size()); assertEquals("First",r.get(a).getTitle()); assertEquals("Second",r.get(b).getTitle()); }
 @Test void basicOperationsWork(){ IssueRegistry r=new IssueRegistry(); IssueId id=new IssueId("PROJ",2); ProjectTask task=new ProjectTask("T2","Task"); r.put(id,task); assertTrue(r.contains(new IssueId("PROJ",2))); assertEquals(1,r.size()); assertSame(task,r.remove(new IssueId("PROJ",2))); assertFalse(r.contains(id)); assertEquals(0,r.size()); }
}
