package state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.state.Blackjack;
import domain.state.Hit;
import domain.state.Ready;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReadyTest {
    @DisplayName("처음 받은 두 장의 합이 21이면 Blackjack 상태가 된다.")
    @Test
    void initialTwoCardsTwentyOne_ThenBlackjack() {
        Ready ready = new Ready(createCards(
                new Card(Denomination.ACE, Suit.SPADE),
                new Card(Denomination.KING, Suit.HEART)
        ));

        State nextState = ready.nextState();

        assertThat(nextState).isInstanceOf(Blackjack.class);
    }

    @DisplayName("처음 받은 두 장의 합이 21 미만이면 Hit 상태가 된다.")
    @Test
    void initialTwoCardsLessThanTwentyOne_ThenHit() {
        Ready ready = new Ready(createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART)
        ));

        State nextState = ready.nextState();

        assertThat(nextState).isInstanceOf(Hit.class);
    }

    @DisplayName("Ready 상태는 처음 받은 두 장의 카드로만 생성할 수 있다.")
    @Test
    void create_Exception_WhenNotInitialTwoCards() {
        Cards cards = createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART),
                new Card(Denomination.ACE, Suit.CLOVER)
        );

        assertThatThrownBy(() -> new Ready(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
