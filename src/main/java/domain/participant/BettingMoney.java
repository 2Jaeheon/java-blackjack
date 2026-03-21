package domain.participant;

import domain.ErrorMessage;
import domain.result.ProfitMoney;
import domain.result.ProfitRate;

public record BettingMoney(int value) {
    private static final int MINIMUM = 100;
    private static final int MAXIMUM = 100_000;
    private static final int UNIT = 100;

    public BettingMoney {
        validateRange(value);
        validateUnit(value);
    }

    private void validateRange(int value) {
        if (value < MINIMUM || value > MAXIMUM) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BETTING_MONEY_RANGE);
        }
    }

    private void validateUnit(int value) {
        if (value % UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BETTING_MONEY_UNIT);
        }
    }

    public ProfitMoney calculateProfit(ProfitRate profitRate) {
        return ProfitMoney.from(this, profitRate);
    }
}
