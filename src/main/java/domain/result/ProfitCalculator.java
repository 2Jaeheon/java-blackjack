package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;

public class ProfitCalculator {
    public ProfitRate calculate(Player player, Dealer dealer) {
        if (player.isBust()) {
            return ProfitRate.LOSE;
        }
        if (isWinning(player, dealer)) {
            return winningProfitRate(player);
        }
        return loseOrDraw(player, dealer);
    }

    public ProfitMoney calculateProfitMoney(Player player, Dealer dealer) {
        return player.getBettingMoney().calculateProfit(calculate(player, dealer));
    }

    public ProfitMoney calculateDealerProfitMoney(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> calculateProfitMoney(player, dealer))
                .reduce(ProfitMoney.ZERO, ProfitMoney::plus)
                .negate();
    }

    private boolean isWinning(Player player, Dealer dealer) {
        return dealer.isBust() || player.calculateScore() > dealer.calculateScore();
    }

    private ProfitRate loseOrDraw(Player player, Dealer dealer) {
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
