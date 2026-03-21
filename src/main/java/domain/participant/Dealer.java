package domain.participant;

import domain.cards.Cards;
import domain.deck.Deck;

public class Dealer extends Participant {
    private static final int DRAW_THRESHOLD = 16;

    public Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer drawInitialCards(Deck deck) {
        return new Dealer(Cards.drawInitialCards(deck));
    }

    public boolean shouldDraw() {
        return calculateScore() <= DRAW_THRESHOLD;
    }

    public int play(Deck deck) {
        int beforeCardCount = cardCount();
        drawUntilStay(deck);
        finishTurn();
        return cardCount() - beforeCardCount;
    }

    private void drawUntilStay(Deck deck) {
        while (shouldDraw()) {
            draw(deck.draw());
        }
    }

    private void finishTurn() {
        if (canDraw()) {
            stay();
        }
    }
}
