package participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingMoneyTest {
    @DisplayName("배팅 금액은 100원 단위로 생성할 수 있다.")
    @Test
    void create() {
        assertThatCode(() -> new BettingMoney(1_000))
                .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액은 100원 미만이면 생성할 수 없다.")
    @Test
    void throwException_WhenUnderMinimum() {
        assertThatThrownBy(() -> new BettingMoney(99))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액은 100000원을 초과하면 생성할 수 없다.")
    @Test
    void throwException_WhenAboveMaximum() {
        assertThatThrownBy(() -> new BettingMoney(100_001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액은 100원 단위가 아니면 생성할 수 없다.")
    @Test
    void throwException_WhenBettingUnit() {
        assertThatThrownBy(() -> new BettingMoney(150))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
