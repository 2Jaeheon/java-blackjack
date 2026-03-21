package domain.participant;

import domain.cards.Cards;
import domain.deck.Deck;

public class Player extends Participant {
    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, Cards cards, BettingMoney bettingMoney) {
        super(cards);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public static Player drawInitialCards(Name name, BettingMoney bettingMoney, Deck deck) {
        return new Player(name, Cards.drawInitialCards(deck), bettingMoney);
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    public Name getName() {
        return name;
    }

    public void hit(Deck deck) {
        draw(deck.draw());
    }
}
