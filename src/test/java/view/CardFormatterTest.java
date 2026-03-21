import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.CardFormatter;

public class CardFormatterTest {
    private final CardFormatter cardFormatter = new CardFormatter();

    @DisplayName("뷰는 카드를 출력 문자열로 변환한다.")
    @Test
    void formatCard() {
        Card card = new Card(Denomination.THREE, Suit.DIAMOND);

        assertEquals("3다이아몬드", cardFormatter.format(card));
    }

    @DisplayName("뷰는 카드 목록을 출력 문자열로 조합한다.")
    @Test
    void formatCards() {
        Cards cards = new Cards();
        cards.add(new Card(Denomination.TWO, Suit.HEART));
        cards.add(new Card(Denomination.EIGHT, Suit.SPADE));

        assertEquals("2하트, 8스페이드", cardFormatter.format(cards));
    }
}
