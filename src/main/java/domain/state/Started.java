package domain.state;

import domain.cards.Cards;

public abstract class Started implements State {
    protected final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public int calculateScore() {
        return cards.calculateScore();
    }
}
