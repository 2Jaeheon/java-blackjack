package result;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.participant.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.ProfitMoney;
import domain.result.ProfitCalculator;
import domain.result.ProfitRate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitCalculatorTest {
    private final ProfitCalculator profitCalculator = new ProfitCalculator();

    @DisplayName("플레이어가 Bust이면 무조건 패배한다.")
    @Test
    void calculateLose_WhenPlayerBust() {
        Player player = createPlayer("pobi",
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.SEVEN, Suit.CLOVER));

        player.draw(new Card(Denomination.THREE, Suit.CLOVER));
        dealer.stay();

        ProfitRate profitRate = profitCalculator.calculate(player, dealer);

        assertEquals(profitRate, ProfitRate.LOSE);
    }

    @DisplayName("딜러가 Bust이고 플레이어가 Bust가 아니면 플레이어는 승리한다.")
    @Test
    void calculateWin_WhenDealerBust() {
        Player player = createPlayer("pobi",
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.CLOVER));

        player.stay();
        dealer.draw(new Card(Denomination.THREE, Suit.HEART));

        ProfitRate profitRate = profitCalculator.calculate(player, dealer);

        assertEquals(profitRate, ProfitRate.WIN);
    }

    @DisplayName("플레이어가 블랙잭으로 승리하면 1.5배의 수익률을 가진다.")
    @Test
    void calculateBlackjackWin() {
        Player player = createPlayer("pobi",
                new Card(Denomination.ACE, Suit.SPADE),
                new Card(Denomination.KING, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.CLOVER));

        dealer.stay();

        ProfitRate profitRate = profitCalculator.calculate(player, dealer);

        assertEquals(profitRate, ProfitRate.BLACKJACK_WIN);
    }

    @DisplayName("둘 다 Stay일 때 플레이어 점수가 더 높으면 승리한다.")
    @Test
    void calculateWin_WhenPlayerScoreHigher() {
        Player player = createPlayer("pobi",
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.SEVEN, Suit.HEART));

        player.stay();
        dealer.stay();

        ProfitRate profitRate = profitCalculator.calculate(player, dealer);

        assertEquals(profitRate, ProfitRate.WIN);
    }

    @DisplayName("둘 다 Stay이고 점수가 같으면 무승부다.")
    @Test
    void calculateDraw_WhenSameScore() {
        Player player = createPlayer("pobi",
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.NINE, Suit.DIAMOND));

        player.stay();
        dealer.stay();

        ProfitRate profitRate = profitCalculator.calculate(player, dealer);

        assertEquals(profitRate, ProfitRate.DRAW);
    }

    @DisplayName("계산기는 플레이어의 실제 수익 금액을 계산한다.")
    @Test
    void calculateProfitMoney() {
        Player player = createPlayer("pobi",
                new Card(Denomination.ACE, Suit.SPADE),
                new Card(Denomination.KING, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.CLOVER));

        dealer.stay();

        ProfitMoney profitMoney = profitCalculator.calculateProfitMoney(player, dealer);

        assertEquals(profitMoney, new ProfitMoney(1_500));
    }

    @DisplayName("계산기는 모든 플레이어 결과를 합산해 딜러 수익금을 계산한다.")
    @Test
    void calculateDealerProfitMoney() {
        Player winningPlayer = createPlayer("pobi",
                new Card(Denomination.TEN, Suit.SPADE),
                new Card(Denomination.NINE, Suit.HEART));
        Player losingPlayer = createPlayer("jason",
                new Card(Denomination.TEN, Suit.CLOVER),
                new Card(Denomination.SIX, Suit.HEART));
        Dealer dealer = createDealer(
                new Card(Denomination.TEN, Suit.DIAMOND),
                new Card(Denomination.SEVEN, Suit.CLOVER));

        winningPlayer.stay();
        losingPlayer.stay();
        dealer.stay();

        ProfitMoney dealerProfitMoney = profitCalculator.calculateDealerProfitMoney(
                List.of(winningPlayer, losingPlayer),
                dealer
        );

        assertEquals(dealerProfitMoney, ProfitMoney.ZERO);
    }

    private Player createPlayer(String name, Card firstCard, Card secondCard) {
        return new Player(
                new Name(name),
                createCards(firstCard, secondCard),
                new BettingMoney(1_000)
        );
    }

    private Dealer createDealer(Card firstCard, Card secondCard) {
        return new Dealer(createCards(firstCard, secondCard));
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
