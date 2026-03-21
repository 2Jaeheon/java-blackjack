package domain.result;

public record ProfitRate(double value) {
    public static final ProfitRate LOSE = new ProfitRate(-1.0);
    public static final ProfitRate DRAW = new ProfitRate(0.0);
    public static final ProfitRate WIN = new ProfitRate(1.0);
    public static final ProfitRate BLACKJACK_WIN = new ProfitRate(1.5);
}
