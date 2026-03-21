package application;

import domain.deck.Deck;
import domain.participant.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ProfitMoney;
import domain.result.ProfitRate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackRound {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackRound(Dealer dealer, Players players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackRound start(PlayerNames playerNames, Map<Name, BettingMoney> bettingMoneys) {
        return start(playerNames, bettingMoneys, Deck.shuffled());
    }

    public static BlackjackRound start(PlayerNames playerNames, Map<Name, BettingMoney> bettingMoneys, Deck deck) {
        Dealer dealer = Dealer.drawInitialCards(deck);
        Players players = new Players(createPlayers(playerNames, bettingMoneys, deck));

        return new BlackjackRound(dealer, players, deck);
    }

    public Dealer dealer() {
        return dealer;
    }

    public Player player(Name name) {
        return players.player(name);
    }

    public void hit(Name name) {
        players.hit(name, deck);
    }

    public boolean canPlayerDraw(Name name) {
        return player(name).canDraw();
    }

    public void applyPlayerDecision(Name name, HitDecision hitDecision) {
        if (hitDecision.isHit()) {
            hit(name);
            return;
        }
        stay(name);
    }

    public void stay(Name name) {
        players.stay(name);
    }

    public int playDealerTurn() {
        return dealer.play(deck);
    }

    public ProfitRate profitRateOf(Name name) {
        return players.profitRateOf(name, dealer);
    }

    public ProfitMoney profitMoneyOf(Name name) {
        return players.profitMoneyOf(name, dealer);
    }

    public ProfitMoney dealerProfitMoney() {
        return players.dealerProfitMoney(dealer);
    }

    private static List<Player> createPlayers(PlayerNames playerNames, Map<Name, BettingMoney> bettingMoneys,
                                              Deck deck) {
        List<Player> players = new ArrayList<>();
        for (Name name : playerNames) {
            players.add(Player.drawInitialCards(name, bettingMoneys.get(name), deck));
        }
        return players;
    }
}
