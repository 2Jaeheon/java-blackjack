package state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.state.Bust;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BustTest {
    @DisplayName("Bust 상태는 카드를 더 이상 뽑을 수 없다.")
    @Test
    void isFinished() {
        Cards cards = new Cards();
        cards.add(new Card(Denomination.KING, Suit.CLOVER));
        cards.add(new Card(Denomination.KING, Suit.HEART));
        cards.add(new Card(Denomination.KING, Suit.DIAMOND));

        State bust = new Bust(cards);

        assertTrue(bust.isFinish());
    }

    @DisplayName("Bust 상태에서 카드를 더 뽑는 경우 예외가 발생한다.")
    @Test
    void draw_Exception() {
        Cards cards = new Cards();
        cards.add(new Card(Denomination.KING, Suit.CLOVER));
        cards.add(new Card(Denomination.KING, Suit.HEART));
        cards.add(new Card(Denomination.KING, Suit.DIAMOND));

        State bust = new Bust(cards);

        assertThatThrownBy(() -> bust.draw(new Card(Denomination.TEN, Suit.SPADE)))
                .isInstanceOf(IllegalStateException.class);
    }
}
