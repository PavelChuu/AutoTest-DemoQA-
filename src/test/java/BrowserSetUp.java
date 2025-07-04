import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

class BrowserSetUp implements ArgumentsProvider {
    static Stream<? extends Arguments> SetUp() {
        return Stream.of(
                Arguments.of("firefox"),
                Arguments.of("chrome"),
                Arguments.of("edge")
        );
    }
}