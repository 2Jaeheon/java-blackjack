package domain.participant;

import domain.cards.Cards;

public class Dealer extends Participant {
    private static final int DRAW_THRESHOLD = 16;

    public Dealer(Name name, Cards cards) {
        super(name, cards);
    }

    public boolean shouldDraw() {
        return calculateScore() <= DRAW_THRESHOLD;
    }
}
