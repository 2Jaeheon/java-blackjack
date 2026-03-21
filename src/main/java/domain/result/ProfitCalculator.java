package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public class ProfitCalculator {
    public ProfitRate calculate(Player player, Dealer dealer) {
        if (player.isBust()) {
            return ProfitRate.LOSE;
        }

        if (dealer.isBust()) {
            return winningProfitRate(player);
        }

        if (player.calculateScore() > dealer.calculateScore()) {
            return winningProfitRate(player);
        }

        if (player.calculateScore() < dealer.calculateScore()) {
            return ProfitRate.LOSE;
        }

        return ProfitRate.DRAW;
    }

    private ProfitRate winningProfitRate(Player player) {
        if (player.isBlackjack()) {
            return ProfitRate.BLACKJACK_WIN;
        }
        return ProfitRate.WIN;
    }
}
