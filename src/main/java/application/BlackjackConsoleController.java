package application;

import domain.participant.BettingMoney;
import domain.participant.Name;
import java.util.LinkedHashMap;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackjackConsoleController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackConsoleController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        PlayerNames playerNames = inputView.readPlayerNames();
        BlackjackRound blackjackRound = BlackjackRound.start(playerNames, readBettingMoneys(playerNames));
        outputView.printInitialDeal(playerNames, blackjackRound);

        playAllPlayerTurns(playerNames, blackjackRound);
        int dealerDrawCount = blackjackRound.playDealerTurn();
        outputView.printDealerDrawMessage(dealerDrawCount);

        outputView.printFinalResult(playerNames, blackjackRound);
        outputView.printFinalProfit(playerNames, blackjackRound);
    }

    private Map<Name, BettingMoney> readBettingMoneys(PlayerNames playerNames) {
        Map<Name, BettingMoney> bettingMoneys = new LinkedHashMap<>();
        for (Name name : playerNames) {
            bettingMoneys.put(name, inputView.readPlayerBettingMoney(name));
        }

        return bettingMoneys;
    }

    private void playAllPlayerTurns(PlayerNames playerNames, BlackjackRound blackjackRound) {
        for (Name name : playerNames) {
            playPlayerTurn(name, blackjackRound);
        }
    }

    private void playPlayerTurn(Name name, BlackjackRound blackjackRound) {
        while (blackjackRound.canPlayerDraw(name)) {
            HitDecision decision = inputView.readPlayerHitDecision(name);

            blackjackRound.applyPlayerDecision(name, decision);
            outputView.printPlayerCards(blackjackRound.player(name));
        }
    }
}
