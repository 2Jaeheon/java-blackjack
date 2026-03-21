package domain.state;

import domain.cards.Card;
import domain.cards.Cards;

public abstract class Done extends Started {

    public static final String ERROR_ALREADY_DONE = "[ERROR] 이미 종료된 상태입니다.";

    public Done(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException(ERROR_ALREADY_DONE);
    }

    @Override
    public final State stay() {
        throw new IllegalStateException(ERROR_ALREADY_DONE);
    }
}
