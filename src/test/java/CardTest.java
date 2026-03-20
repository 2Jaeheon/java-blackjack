import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.cards.Card;
import domain.cards.Denomination;
import domain.cards.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {
    @DisplayName("카드는 숫자와 문양으로 조합되어 정상적으로 생성된다.")
    @Test
    void DenominationAndSuit_Then_Card() {
        Card card = new Card(Denomination.ACE, Suit.SPADE);

        assertEquals(card, new Card(Denomination.ACE, Suit.SPADE));
    }

    @DisplayName("카드는 자신의 기본 점수를 반환할 수 있다")
    @Test
    void shouldReturnBasicScore() {
        Card card = new Card(Denomination.ACE, Suit.HEART);

        Denomination denomination = card.denomination();

        assertEquals(denomination, Denomination.ACE);
    }

    @DisplayName("카드가 에이스인지 확인할 수 있다")
    @Test
    void isAce() {
        Card card = new Card(Denomination.ACE, Suit.HEART);

        boolean aceCard = card.isAce();

        assertTrue(aceCard);
    }

    @DisplayName("카드는 자신의 기본 점수를 반환할 수 있다.")
    @Test
    void getScore() {
        Card aceCard = new Card(Denomination.ACE, Suit.SPADE);
        Card tenCard = new Card(Denomination.TEN, Suit.HEART);
        Card kingCard = new Card(Denomination.KING, Suit.CLOVER);
        Card fiveCard = new Card(Denomination.FIVE, Suit.DIAMOND);

        assertEquals(aceCard.getScore(), 11);
        assertEquals(tenCard.getScore(), 10);
        assertEquals(kingCard.getScore(), 10);
        assertEquals(fiveCard.getScore(), 5);
    }
}
