package domain.participant;

import domain.cards.Cards;
import domain.deck.Deck;
import domain.result.ProfitMoney;
import domain.result.ProfitRate;

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

    public ProfitRate profitRateAgainst(Dealer dealer) {
        if (isBust()) {
            return ProfitRate.LOSE;
        }
        if (isWinningAgainst(dealer)) {
            return winningProfitRate();
        }
        return loseOrDrawAgainst(dealer);
    }

    private boolean hasHigherScoreThan(Dealer dealer) {
        return calculateScore() > dealer.calculateScore();
    }

    private boolean isWinningAgainst(Dealer dealer) {
        return dealer.isBust() || hasHigherScoreThan(dealer);
    }

    private boolean hasLowerScoreThan(Dealer dealer) {
        return calculateScore() < dealer.calculateScore();
    }

    private ProfitRate loseOrDrawAgainst(Dealer dealer) {
        if (hasLowerScoreThan(dealer)) {
            return ProfitRate.LOSE;
        }
        return ProfitRate.DRAW;
    }

    private ProfitRate winningProfitRate() {
        if (isBlackjack()) {
            return ProfitRate.BLACKJACK_WIN;
        }
        return ProfitRate.WIN;
    }

    public ProfitMoney profitMoneyAgainst(Dealer dealer) {
        return bettingMoney.calculateProfit(profitRateAgainst(dealer));
    }
}
