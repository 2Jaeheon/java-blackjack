package domain.participant;

import domain.cards.Cards;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(Name name, Cards cards, BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
