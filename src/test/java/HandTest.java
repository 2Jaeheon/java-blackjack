import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.cards.Card;
import domain.cards.Denomination;
import domain.cards.Hand;
import domain.cards.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HandTest {
    @DisplayName("A가 없는 일반 카드 여러 장의 점수 합을 계산한다.")
    @Test
    void calculateScore_withoutAce() {
        Hand hand = new Hand();

        hand.add(new Card(Denomination.TEN, Suit.SPADE));
        hand.add(new Card(Denomination.FIVE, Suit.SPADE));

        int score = hand.calculateScore();

        assertEquals(score, 15);
    }

    @DisplayName("A가 포함되어있고, 21을 초과하는 경우 A를 1점으로 계산한다.")
    @Test
    void calculateScore_WithAce_AndBust() {
        Hand hand = new Hand();
        hand.add(new Card(Denomination.ACE, Suit.SPADE));
        hand.add(new Card(Denomination.TEN, Suit.HEART));
        hand.add(new Card(Denomination.TWO, Suit.CLOVER));

        int score = hand.calculateScore();

        assertEquals(score, 13);
    }

    @DisplayName("A가 여러 장일 때, 21점을 초과하는 경우 A를 1점과 11점으로 계산한다.")
    @Test
    void calculateScore_WithManyAces() {
        Hand hand = new Hand();
        hand.add(new Card(Denomination.ACE, Suit.SPADE));
        hand.add(new Card(Denomination.ACE, Suit.HEART));
        hand.add(new Card(Denomination.TEN, Suit.CLOVER));
        assertEquals(hand.calculateScore(), 12);
    }
}
