package domain.state;

import domain.cards.Cards;

public class Bust extends Done {
    public Bust(Cards cards) {
        super(cards);
    }
}
