import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
class MutableKeyExperimentTest {
 static class MutableKey { String value; MutableKey(String value){this.value=value;} @Override public boolean equals(Object o){if(this==o)return true;if(!(o instanceof MutableKey))return false;return value.equals(((MutableKey)o).value);} @Override public int hashCode(){return value.hashCode();} }
 @Test void changingKeyCanBreakHashMapLookup(){ Map<MutableKey,String> map=new HashMap<>(); MutableKey key=new MutableKey("A"); map.put(key,"value"); key.value="B"; assertNull(map.get(key)); assertEquals(1,map.size()); }
}
