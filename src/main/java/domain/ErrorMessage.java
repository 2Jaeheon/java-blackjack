package domain;

public final class ErrorMessage {
    public static final String ALREADY_DONE = "[ERROR] 이미 종료된 상태입니다.";
    public static final String NOT_BLACKJACK = "[ERROR] 블랙잭 상태가 아닙니다.";
    public static final String NOT_INITIAL_TWO_CARDS = "[ERROR] Ready 상태는 처음 받은 두 장의 카드로만 생성할 수 있습니다.";
    public static final String READY_STATE_TRANSITION_ONLY = "[ERROR] Ready 상태에서는 nextState()를 통해 상태를 결정해야 합니다.";

    private ErrorMessage() {
    }
}
