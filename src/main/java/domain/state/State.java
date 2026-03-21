package domain.state;

import domain.cards.Card;
import domain.cards.Cards;

public interface State {
    State draw(Card card);

    State stay();

    boolean isFinish();

    Cards getCards();
}
