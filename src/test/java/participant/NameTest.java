package participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {
    @DisplayName("이름은 5자 이하로 생성할 수 있다.")
    @Test
    void create() {
        assertThatCode(() -> new Name("pobi"))
                .doesNotThrowAnyException();
    }

    @DisplayName("이름은 공백이면 생성할 수 없다.")
    @Test
    void create_Exception_WhenBlank() {
        assertThatThrownBy(() -> new Name(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름은 5자를 초과하면 생성할 수 없다.")
    @Test
    void create_Exception_WhenTooLong() {
        assertThatThrownBy(() -> new Name("abcdef"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름은 출력에 사용할 은는 조사를 반환할 수 있다.")
    @Test
    void topicMarker() {
        assertEquals(new Name("pobi").topicMarker(), "는");
        assertEquals(new Name("jason").topicMarker(), "은");
    }
}
