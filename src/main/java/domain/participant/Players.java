package domain.participant;

import domain.ErrorMessage;
import domain.deck.Deck;
import domain.result.ProfitMoney;
import domain.result.ProfitCalculator;
import domain.result.ProfitRate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;
    private final ProfitCalculator profitCalculator = new ProfitCalculator();

    public Players(List<Player> players) {
        validateDuplicateName(players);
        this.players = List.copyOf(players);
    }

    public int size() {
        return players.size();
    }

    public Player player(Name name) {
        return findPlayer(name);
    }

    public void hit(Name name, Deck deck) {
        findPlayer(name).hit(deck);
    }

    public void stay(Name name) {
        findPlayer(name).stay();
    }

    public ProfitRate profitRateOf(Name name, Dealer dealer) {
        return profitCalculator.calculate(findPlayer(name), dealer);
    }

    public ProfitMoney profitMoneyOf(Name name, Dealer dealer) {
        return profitCalculator.calculateProfitMoney(findPlayer(name), dealer);
    }

    public ProfitMoney dealerProfitMoney(Dealer dealer) {
        return profitCalculator.calculateDealerProfitMoney(players, dealer);
    }

    private void validateDuplicateName(List<Player> players) {
        Set<Name> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (names.size() != players.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_PLAYER_NAME);
        }
    }

    private Player findPlayer(Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.PLAYER_NOT_FOUND));
    }
}
