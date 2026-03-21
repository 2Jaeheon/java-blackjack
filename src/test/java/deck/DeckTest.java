package deck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.cards.Card;
import domain.deck.Deck;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {
    @DisplayName("Deck은 생성 시 중복 없는 52장의 카드를 가진다.")
    @Test
    void create() {
        Deck deck = new Deck();
        Set<Card> cards = new HashSet<>();

        for (int count = 0; count < 52; count++) {
            cards.add(deck.draw());
        }

        assertEquals(cards.size(), 52);
        assertEquals(deck.size(), 0);
    }

    @DisplayName("Deck에서 카드를 한 장 뽑으면 남은 카드 수가 1 감소한다.")
    @Test
    void draw() {
        Deck deck = new Deck();

        deck.draw();

        assertEquals(deck.size(), 51);
    }

    @DisplayName("Deck의 카드가 모두 소진된 경우 카드를 뽑으면 예외가 발생한다.")
    @Test
    void draw_Exception_WhenDeckIsEmpty() {
        Deck deck = new Deck();

        for (int count = 0; count < 52; count++) {
            deck.draw();
        }

        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalStateException.class);
    }
}
