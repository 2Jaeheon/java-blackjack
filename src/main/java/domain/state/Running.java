package domain.state;

import domain.cards.Card;
import domain.cards.Cards;

public abstract class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public final State draw(Card card) {
        cards.add(card);

        int score = cards.calculateScore();
        if (score > Cards.BLACKJACK) {
            return new Bust(cards);
        }
        if (score == Cards.BLACKJACK) {
            return new Stay(cards);
        }
        return new Hit(cards);
    }

    @Override
    public final State stay() {
        return new Stay(cards);
    }
}
