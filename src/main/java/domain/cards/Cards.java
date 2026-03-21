package domain.cards;

import domain.deck.Deck;
import java.util.LinkedList;
import java.util.List;

public class Cards {
    public static final int BLACKJACK = 21;
    public static final int ACE_DISCOUNT_VALUE = 10;
    private static final int INITIAL_CARD_COUNT = 2;
    private final List<Card> cards = new LinkedList<>();

    public static Cards drawInitialCards(Deck deck) {
        Cards cards = new Cards();
        cards.addInitialCards(deck);
        return cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = calculateRawScore();
        int aceCount = countAces();

        return adjustAceScore(score, aceCount);
    }

    public boolean isBlackjack() {
        if (cards.size() == 2 && calculateScore() == BLACKJACK) {
            return true;
        }

        return false;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> asList() {
        return List.copyOf(cards);
    }

    private void addInitialCards(Deck deck) {
        for (int count = 0; count < INITIAL_CARD_COUNT; count++) {
            add(deck.draw());
        }
    }

    private int adjustAceScore(int score, int aceCount) {
        int adjustedScore = score;
        int remainAce = aceCount;

        while (adjustedScore > BLACKJACK && remainAce > 0) {
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
