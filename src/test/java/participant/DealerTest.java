package participant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.participant.Dealer;
import domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("Dealer는 점수가 16 이하면 카드를 더 뽑아야 한다.")
    @Test
    void shouldDraw_WhenDealerUnderSixteenScore() {
        Dealer dealer = new Dealer(
                new Name("bank"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.SIX, Suit.HEART)
                )
        );

        assertTrue(dealer.shouldDraw());
    }

    @DisplayName("Dealer는 점수가 17 이상이면 카드를 더 뽑지 않는다.")
    @Test
    void shouldNotDraw_WhenDealerUnderSeventeenScore() {
        Dealer dealer = new Dealer(
                new Name("bank"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.SEVEN, Suit.HEART)
                )
        );

        assertFalse(dealer.shouldDraw());
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
