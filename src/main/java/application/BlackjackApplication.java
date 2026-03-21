package application;

import view.InputView;
import view.OutputView;

public class BlackjackApplication {
    public static void main(String[] args) {
        new BlackjackConsoleController(new InputView(), new OutputView()).run();
    }
}
