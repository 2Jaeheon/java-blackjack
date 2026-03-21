package domain.state;

import domain.ErrorMessage;
import domain.cards.Card;
import domain.cards.Cards;

public class Ready extends Started {
    private static final int INITIAL_CARD_COUNT = 2;

    public Ready(Cards cards) {
        super(cards);
        validateInitialCards(cards);
    }

    public State nextState() {
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(ErrorMessage.READY_STATE_TRANSITION_ONLY);
    }

    @Override
    public State stay() {
        throw new IllegalStateException(ErrorMessage.READY_STATE_TRANSITION_ONLY);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    private void validateInitialCards(Cards cards) {
        if (cards.size() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.NOT_INITIAL_TWO_CARDS);
        }
    }
}
