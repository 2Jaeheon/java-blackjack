package domain.participant;

import domain.cards.Card;
import domain.cards.Cards;
import domain.state.Ready;
import domain.state.State;

public abstract class Participant {
    private State state;

    protected Participant(Cards cards) {
        this.state = new Ready(cards).nextState();
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isFinish() {
        return state.isFinish();
    }

    public boolean canDraw() {
        return state.canDraw();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public Cards getCards() {
        return state.getCards();
    }

    public int calculateScore() {
        return state.calculateScore();
    }

    public int cardCount() {
        return state.cardCount();
    }
}
