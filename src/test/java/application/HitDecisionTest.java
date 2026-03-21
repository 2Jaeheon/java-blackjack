package application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitDecisionTest {
    @DisplayName("y를 입력하면 HIT 결정을 반환한다.")
    @Test
    void fromHit() {
        assertThat(HitDecision.from("y")).isEqualTo(HitDecision.HIT);
    }

    @DisplayName("n을 입력하면 STAY 결정을 반환한다.")
    @Test
    void fromStay() {
        assertThat(HitDecision.from("n")).isEqualTo(HitDecision.STAY);
    }

    @DisplayName("y 또는 n이 아니면 예외가 발생한다.")
    @Test
    void fromException() {
        assertThatThrownBy(() -> HitDecision.from("x"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
