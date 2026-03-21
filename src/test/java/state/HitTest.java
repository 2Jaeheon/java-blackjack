package state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.state.Bust;
import domain.state.Hit;
import domain.state.State;
import domain.state.Stay;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HitTest {
    @DisplayName("Hit 상태는 종료 상태가 아니다.")
    @Test
    void isNotFinished() {
        State hit = new Hit(createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART)
        ));

        assertFalse(hit.isFinish());
    }

    @DisplayName("Hit 상태에서 카드를 더 뽑아 21 미만이면 Hit 상태를 유지한다.")
    @Test
    void draw_ThenHit() {
        State hit = new Hit(createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.FIVE, Suit.HEART)
        ));

        State nextState = hit.draw(new Card(Denomination.THREE, Suit.CLOVER));

        assertThat(nextState).isInstanceOf(Hit.class);
        assertThat(nextState.getCards().calculateScore()).isEqualTo(18);
    }

    @DisplayName("Hit 상태에서 카드를 더 뽑아 21이 되면 Stay 상태로 전이한다.")
    @Test
    void draw_ThenStay() {
        State hit = new Hit(createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.FIVE, Suit.HEART)
        ));

        State nextState = hit.draw(new Card(Denomination.SIX, Suit.CLOVER));

        assertThat(nextState).isInstanceOf(Stay.class);
        assertThat(nextState.getCards().calculateScore()).isEqualTo(21);
    }

    @DisplayName("Hit 상태에서 카드를 더 뽑아 21을 초과하면 Bust 상태로 전이한다.")
    @Test
    void draw_ThenBust() {
        State hit = new Hit(createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART)
        ));

        State nextState = hit.draw(new Card(Denomination.THREE, Suit.CLOVER));

        assertThat(nextState).isInstanceOf(Bust.class);
        assertThat(nextState.getCards().calculateScore()).isEqualTo(22);
    }

    @DisplayName("Hit 상태에서 stay를 선택하면 Stay 상태로 전이한다.")
    @Test
    void stay_ThenStay() {
        State hit = new Hit(createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.SEVEN, Suit.HEART)
        ));

        State nextState = hit.stay();

        assertThat(nextState).isInstanceOf(Stay.class);
        assertThat(nextState.getCards().calculateScore()).isEqualTo(17);
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
