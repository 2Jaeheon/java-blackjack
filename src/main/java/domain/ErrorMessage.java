package domain;

public final class ErrorMessage {
    public static final String ALREADY_DONE = "[ERROR] 이미 종료된 상태입니다.";
    public static final String NOT_BLACKJACK = "[ERROR] 블랙잭 상태가 아닙니다.";
    public static final String NOT_INITIAL_TWO_CARDS = "[ERROR] Ready 상태는 처음 받은 두 장의 카드로만 생성할 수 있습니다.";
    public static final String READY_STATE_TRANSITION_ONLY = "[ERROR] Ready 상태에서는 nextState()를 통해 상태를 결정해야 합니다.";
    public static final String INVALID_NAME = "[ERROR] 이름은 공백일 수 없습니다.";
    public static final String NAME_TOO_LONG = "[ERROR] 이름은 5자를 초과할 수 없습니다.";
    public static final String DUPLICATE_PLAYER_NAME = "[ERROR] 플레이어 이름은 중복될 수 없습니다.";
    public static final String INVALID_BETTING_MONEY_RANGE = "[ERROR] 배팅 금액은 100원 이상 100000원 이하여야 합니다.";
    public static final String INVALID_BETTING_MONEY_UNIT = "[ERROR] 배팅 금액은 100원 단위여야 합니다.";
    public static final String EMPTY_DECK = "[ERROR] 덱에 남은 카드가 없습니다.";

    private ErrorMessage() {
    }
}
