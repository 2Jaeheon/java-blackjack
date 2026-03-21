package participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.participant.Name;
import domain.participant.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantTest {
    @DisplayName("Participant는 이름을 가지고 초기 카드 상태를 가진다.")
    @Test
    void create() {
        Participant participant = new TestParticipant(
                new Name("pobi"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.NINE, Suit.HEART)
                ));

        assertEquals(participant.getName(), new Name("pobi"));
        assertEquals(participant.calculateScore(), 19);
        assertFalse(participant.isFinish());
    }

    @DisplayName("Participant는 draw 요청을 현재 상태에 위임한다.")
    @Test
    void draw_DelegatesToState() {
        Participant participant = new TestParticipant(
                new Name("pobi"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.FIVE, Suit.HEART)
                ));

        participant.draw(new Card(Denomination.THREE, Suit.CLOVER));

        assertEquals(participant.calculateScore(), 18);
        assertEquals(participant.getCards().size(), 3);
        assertFalse(participant.isFinish());
    }

    @DisplayName("Participant는 stay 요청을 현재 상태에 위임한다.")
    @Test
    void stay_DelegatesToState() {
        Participant participant = new TestParticipant(new Name("pobi"), createCards(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.SEVEN, Suit.HEART)
        ));

        participant.stay();

        assertTrue(participant.isFinish());
        assertEquals(participant.calculateScore(), 17);
    }

    @DisplayName("Participant는 bust가 되면 종료 상태가 된다.")
    @Test
    void draw_ThenBust() {
        Participant participant = new TestParticipant(
                new Name("pobi"),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.NINE, Suit.HEART)
                ));

        participant.draw(new Card(Denomination.THREE, Suit.CLOVER));

        assertTrue(participant.isFinish());
        assertEquals(participant.calculateScore(), 22);
    }

    @DisplayName("Participant는 초기 카드가 블랙잭이면 종료 상태로 시작한다.")
    @Test
    void initialBlackjack() {
        Participant participant = new TestParticipant(
                new Name("pobi"),
                createCards(
                        new Card(Denomination.ACE, Suit.SPADE),
                        new Card(Denomination.KING, Suit.HEART)
                ));

        assertTrue(participant.isFinish());
        assertEquals(participant.calculateScore(), 21);
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }

    private static class TestParticipant extends Participant {
        private TestParticipant(Name name, Cards cards) {
            super(name, cards);
        }
    }
}
