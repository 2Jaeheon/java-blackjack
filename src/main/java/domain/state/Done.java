package domain.state;

import domain.ErrorMessage;
import domain.cards.Card;
import domain.cards.Cards;

public abstract class Done extends Started {

    public Done(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException(ErrorMessage.ALREADY_DONE);
    }

    @Override
    public final State stay() {
        throw new IllegalStateException(ErrorMessage.ALREADY_DONE);
    }
}
