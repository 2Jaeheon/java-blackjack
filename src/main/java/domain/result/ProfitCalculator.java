package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public class ProfitCalculator {
    public ProfitRate calculate(Player player, Dealer dealer) {
        return player.profitRateAgainst(dealer);
    }
}
