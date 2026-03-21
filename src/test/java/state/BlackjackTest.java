package state;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.state.Blackjack;
import domain.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackTest {
    @DisplayName("처음 받은 2장으로 21일 때 블랙잭 상태를 생성할 수 있다.")
    @Test
    void initialCardsTwentyOne_ThenBlackjack() {
        Cards cards = createCards(
                new Card(Denomination.ACE, Suit.CLOVER),
                new Card(Denomination.JACK, Suit.SPADE)
        );

        assertThatCode(() -> new Blackjack(cards))
                .doesNotThrowAnyException();
    }

    @DisplayName("처음 받은 두 장의 합이 21이 아니면 Blackjack 상태를 생성할 수 없다.")
    @Test
    void create_Exception_WhenScoreIsNotBlackjack() {
        Cards cards = createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART)
        );

        assertThatThrownBy(() -> new Blackjack(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("세 장 이상으로 21을 만든 경우 Blackjack 상태를 생성할 수 없다.")
    @Test
    void create_Exception_WhenNotInitialTwoCards() {
        Cards cards = createCards(
                new Card(Denomination.SEVEN, Suit.SPADE),
                new Card(Denomination.SEVEN, Suit.HEART),
                new Card(Denomination.SEVEN, Suit.CLOVER)
        );

        assertThatThrownBy(() -> new Blackjack(cards))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Blackjack 상태는 종료 상태다.")
    @Test
    void isFinished() {
        State blackjack = new Blackjack(createCards(
                new Card(Denomination.ACE, Suit.SPADE),
                new Card(Denomination.KING, Suit.HEART)
        ));

        assertTrue(blackjack.isFinish());
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
