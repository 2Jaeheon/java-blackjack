package participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.deck.Deck;
import domain.participant.BettingMoney;
import domain.participant.Name;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @DisplayName("Player는 이름, 초기 카드 상태, 배팅 금액을 가진다.")
    @Test
    void create() {
        Player player = new Player(
                new Name("pobi"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.NINE, Suit.HEART)
                ),
                new BettingMoney(1_000)
        );

        assertEquals(player.getName(), new Name("pobi"));
        assertEquals(player.getBettingMoney(), new BettingMoney(1_000));
        assertEquals(player.calculateScore(), 19);
        assertFalse(player.isFinish());
    }

    @DisplayName("Player는 Participant의 공통 행동을 그대로 사용한다.")
    @Test
    void draw() {
        Player player = new Player(
                new Name("pobi"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.FIVE, Suit.HEART)
                ),
                new BettingMoney(1_000)
        );

        player.hit(Deck.from(new Card(Denomination.THREE, Suit.CLOVER)));

        assertEquals(player.calculateScore(), 18);
        assertFalse(player.isFinish());
    }
    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
