package application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.Name;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerNamesTest {
    @DisplayName("쉼표로 구분된 이름 문자열을 PlayerNames로 변환할 수 있다.")
    @Test
    void from() {
        PlayerNames playerNames = PlayerNames.from("pobi, jason");

        assertThat(playerNames).containsExactly(new Name("pobi"), new Name("jason"));
    }

    @DisplayName("중복된 이름이 있으면 예외가 발생한다.")
    @Test
    void fromExceptionWhenDuplicate() {
        assertThatThrownBy(() -> new PlayerNames(List.of(new Name("pobi"), new Name("pobi"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("출력용 이름 문자열을 반환할 수 있다.")
    @Test
    void joinedValues() {
        PlayerNames playerNames = PlayerNames.from("pobi,jason");

        assertThat(playerNames.joinedValues()).isEqualTo("pobi, jason");
    }
}
