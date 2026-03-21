package view;

import application.BlackjackRound;
import application.PlayerNames;
import domain.cards.Card;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;

public class OutputView {
    private final CardFormatter cardFormatter = new CardFormatter();

    public void printInitialDeal(PlayerNames playerNames, BlackjackRound blackjackRound) {
        System.out.println();
        System.out.println("딜러와 " + playerNames.joinedValues() + "에게 2장을 나누었습니다.");
        System.out.println("딜러: " + formatCard(blackjackRound.dealer().getCards().asList().getFirst()));
        printPlayerCards(playerNames, blackjackRound);
        System.out.println();
    }

    public void printPlayerCards(Player player) {
        System.out.println(player.getName().value() + "카드: " + formatCards(player));
    }

    public void printDealerDrawMessage(int drawCount) {
        if (drawCount == 0) {
            return;
        }
        if (drawCount == 1) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 16이하라 카드를 더 받았습니다.");
    }

    public void printFinalResult(PlayerNames playerNames, BlackjackRound blackjackRound) {
        System.out.println();
        printDealerResult(blackjackRound.dealer());
        printPlayerResults(playerNames, blackjackRound);
        System.out.println();
    }

    public void printFinalProfit(PlayerNames playerNames, BlackjackRound blackjackRound) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + blackjackRound.dealerProfitMoney().value());
        printPlayerProfits(playerNames, blackjackRound);
    }

    private void printPlayerCards(PlayerNames playerNames, BlackjackRound blackjackRound) {
        for (Name name : playerNames) {
            printPlayerCards(blackjackRound.player(name));
        }
    }

    private void printDealerResult(Dealer dealer) {
        System.out.println("딜러 카드: " + formatCards(dealer) + " - 결과: " + dealer.calculateScore());
    }

    private void printPlayerResults(PlayerNames playerNames, BlackjackRound blackjackRound) {
        for (Name name : playerNames) {
            printPlayerResult(blackjackRound.player(name));
        }
    }

    private void printPlayerResult(Player player) {
        System.out.println(player.getName().value() + "카드: " + formatCards(player)
                + " - 결과: " + player.calculateScore());
    }

    private void printPlayerProfits(PlayerNames playerNames, BlackjackRound blackjackRound) {
        for (Name name : playerNames) {
            printPlayerProfit(blackjackRound.player(name), blackjackRound);
        }
    }

    private void printPlayerProfit(Player player, BlackjackRound blackjackRound) {
        System.out.println(player.getName().value() + ": " + blackjackRound.profitMoneyOf(player.getName()).value());
    }

    private String formatCards(Player player) {
        return cardFormatter.format(player.getCards());
    }

    private String formatCards(Dealer dealer) {
        return cardFormatter.format(dealer.getCards());
    }

    private String formatCard(Card card) {
        return cardFormatter.format(card);
    }
}
