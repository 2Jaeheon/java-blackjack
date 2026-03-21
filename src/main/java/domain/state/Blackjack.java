package domain.state;

import domain.cards.Cards;

public class Blackjack extends Done {
    public static final String ERROR_NOT_BLACKJACK = "[ERROR] 블랙잭 상태가 아닙니다.";

    public Blackjack(Cards cards) {
        super(cards);
        validateBlackjack(cards);
    }

    private void validateBlackjack(Cards cards) {
        if (!cards.isBlackjack()) {
            throw new IllegalArgumentException(ERROR_NOT_BLACKJACK);
        }
    }
}
