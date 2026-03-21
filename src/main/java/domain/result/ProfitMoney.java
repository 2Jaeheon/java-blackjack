package domain.result;

import domain.participant.BettingMoney;
import java.math.BigDecimal;

public record ProfitMoney(int value) {
    public static final ProfitMoney ZERO = new ProfitMoney(0);

    public static ProfitMoney from(BettingMoney bettingMoney, ProfitRate profitRate) {
        BigDecimal money = BigDecimal.valueOf(bettingMoney.value());
        BigDecimal rate = BigDecimal.valueOf(profitRate.value());
        return new ProfitMoney(money.multiply(rate).intValueExact());
    }

    public ProfitMoney plus(ProfitMoney profitMoney) {
        return new ProfitMoney(value + profitMoney.value);
    }

    public ProfitMoney negate() {
        return new ProfitMoney(-value);
    }
}
