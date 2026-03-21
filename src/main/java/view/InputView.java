package view;

import application.HitDecision;
import application.PlayerNames;
import domain.participant.BettingMoney;
import domain.participant.Name;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public PlayerNames readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return readValidPlayerNames();
    }

    public BettingMoney readPlayerBettingMoney(Name name) {
        System.out.println(name.value() + "의 배팅 금액은?");
        return readValidBettingMoney(name);
    }

    public HitDecision readPlayerHitDecision(Name name) {
        System.out.println(name.value() + name.topicMarker() + " 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return readValidHitDecision(name);
    }

    private PlayerNames readValidPlayerNames() {
        try {
            return PlayerNames.from(scanner.nextLine());
        } catch (IllegalArgumentException exception) {
            printError(exception);
            return readPlayerNames();
        }
    }

    private BettingMoney readValidBettingMoney(Name name) {
        try {
            return new BettingMoney(Integer.parseInt(scanner.nextLine().trim()));
        } catch (IllegalArgumentException exception) {
            printError(exception);
            return readPlayerBettingMoney(name);
        }
    }

    private HitDecision readValidHitDecision(Name name) {
        try {
            return HitDecision.from(scanner.nextLine());
        } catch (IllegalArgumentException exception) {
            printError(exception);
            return readPlayerHitDecision(name);
        }
    }

    private void printError(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
