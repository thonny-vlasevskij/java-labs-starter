import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StreamPreparationTest {

    @Test
    void filtersMapsAndSortsWithoutChangingOriginalList() {
        List<String> words = new ArrayList<>(
                List.of("java", "", "git", "maven", "api"));
        List<String> original = new ArrayList<>(words);

        List<String> result = words.stream()
                .filter(word -> !word.isEmpty())
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .toList();

        assertEquals(List.of("API", "GIT", "JAVA", "MAVEN"), result);
        assertEquals(original, words);
    }
}
