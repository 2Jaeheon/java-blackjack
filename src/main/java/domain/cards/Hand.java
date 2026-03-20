package domain.cards;

import java.util.LinkedList;
import java.util.List;

public class Hand {
    public static final int BLACKJACK_MAX_SCORE = 21;
    public static final int ACE_DISCOUNT_VALUE = 10;
    private final List<Card> cards = new LinkedList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = calculateRawScore();
        int aceCount = countAces();

        return adjustAceScore(score, aceCount);
    }

    private int adjustAceScore(int score, int aceCount) {
        int adjustedScore = score;
        int remainAce = aceCount;

        while (adjustedScore > BLACKJACK_MAX_SCORE && remainAce > 0) {
            adjustedScore -= ACE_DISCOUNT_VALUE;
            remainAce--;
        }

        return adjustedScore;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAces() {
        return Math.toIntExact(cards.stream()
                .filter(Card::isAce)
                .count());
    }
}
