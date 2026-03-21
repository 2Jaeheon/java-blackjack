package domain.state;

import domain.ErrorMessage;
import domain.cards.Cards;

public class Blackjack extends Done {
    public Blackjack(Cards cards) {
        super(cards);
        validateBlackjack(cards);
    }

    private void validateBlackjack(Cards cards) {
        if (!cards.isBlackjack()) {
            throw new IllegalArgumentException(ErrorMessage.NOT_BLACKJACK);
        }
    }
}
