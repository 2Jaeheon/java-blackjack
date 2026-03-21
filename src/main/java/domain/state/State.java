package domain.state;

import domain.cards.Card;
import domain.cards.Cards;

public interface State {
    State draw(Card card);

    State stay();

    boolean canDraw();

    boolean isFinish();

    boolean isBlackjack();

    boolean isBust();

    int calculateScore();

    int cardCount();

    Cards getCards();
}
