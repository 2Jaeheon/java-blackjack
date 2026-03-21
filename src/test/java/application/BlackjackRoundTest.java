package application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.cards.Card;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.deck.Deck;
import domain.participant.BettingMoney;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.ProfitMoney;
import domain.result.ProfitRate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackRoundTest {
    @DisplayName("게임 생성 시 딜러와 플레이어는 초기 카드 두 장씩을 받는다.")
    @Test
    void create() {
        Deck deck = Deck.from(
                createCard(Denomination.TEN, Suit.SPADE),
                createCard(Denomination.SIX, Suit.HEART),
                createCard(Denomination.TEN, Suit.DIAMOND),
                createCard(Denomination.NINE, Suit.CLOVER),
                createCard(Denomination.EIGHT, Suit.HEART),
                createCard(Denomination.SEVEN, Suit.SPADE)
        );
        BlackjackRound blackjackRound = new BlackjackRound(
                Dealer.drawInitialCards(deck),
                createPlayers(deck, "pobi", "jason"),
                deck
        );

        assertEquals(blackjackRound.dealer().cardCount(), 2);
        assertEquals(blackjackRound.dealer().calculateScore(), 16);
        assertEquals(blackjackRound.player(new Name("pobi")).calculateScore(), 19);
        assertEquals(blackjackRound.player(new Name("jason")).calculateScore(), 15);
    }

    @DisplayName("플레이어가 hit를 선택하면 덱에서 카드를 한 장 더 받는다.")
    @Test
    void hit() {
        Deck deck = Deck.from(
                createCard(Denomination.TEN, Suit.SPADE),
                createCard(Denomination.SIX, Suit.HEART),
                createCard(Denomination.TEN, Suit.DIAMOND),
                createCard(Denomination.FIVE, Suit.CLOVER),
                createCard(Denomination.THREE, Suit.HEART)
        );
        BlackjackRound blackjackRound = new BlackjackRound(
                Dealer.drawInitialCards(deck),
                createPlayers(deck, "pobi"),
                deck
        );

        blackjackRound.hit(new Name("pobi"));

        assertEquals(blackjackRound.player(new Name("pobi")).cardCount(), 3);
        assertEquals(blackjackRound.player(new Name("pobi")).calculateScore(), 18);
        assertThat(blackjackRound.player(new Name("pobi")).isFinish()).isFalse();
    }

    @DisplayName("플레이어가 stay를 선택하면 종료 상태가 된다.")
    @Test
    void stay() {
        Deck deck = Deck.from(
                createCard(Denomination.TEN, Suit.SPADE),
                createCard(Denomination.SIX, Suit.HEART),
                createCard(Denomination.TEN, Suit.DIAMOND),
                createCard(Denomination.FIVE, Suit.CLOVER)
        );
        BlackjackRound blackjackRound = new BlackjackRound(
                Dealer.drawInitialCards(deck),
                createPlayers(deck, "pobi"),
                deck
        );

        blackjackRound.stay(new Name("pobi"));

        assertThat(blackjackRound.player(new Name("pobi")).isFinish()).isTrue();
        assertEquals(blackjackRound.player(new Name("pobi")).calculateScore(), 15);
    }

    @DisplayName("딜러는 16 이하면 카드를 뽑고 17 이상이 되면 멈춘다.")
    @Test
    void playDealerTurn() {
        Deck deck = Deck.from(
                createCard(Denomination.TEN, Suit.SPADE),
                createCard(Denomination.SIX, Suit.HEART),
                createCard(Denomination.TEN, Suit.DIAMOND),
                createCard(Denomination.NINE, Suit.CLOVER),
                createCard(Denomination.ACE, Suit.HEART)
        );
        BlackjackRound blackjackRound = new BlackjackRound(
                Dealer.drawInitialCards(deck),
                createPlayers(deck, "pobi"),
                deck
        );

        blackjackRound.playDealerTurn();

        assertEquals(blackjackRound.dealer().cardCount(), 3);
        assertEquals(blackjackRound.dealer().calculateScore(), 17);
        assertThat(blackjackRound.dealer().isFinish()).isTrue();
    }

    @DisplayName("정산 시 플레이어의 수익률을 조회할 수 있다.")
    @Test
    void profitRateOf() {
        Deck deck = Deck.from(
                createCard(Denomination.TEN, Suit.SPADE),
                createCard(Denomination.SEVEN, Suit.HEART),
                createCard(Denomination.TEN, Suit.DIAMOND),
                createCard(Denomination.NINE, Suit.CLOVER)
        );
        BlackjackRound blackjackRound = new BlackjackRound(
                Dealer.drawInitialCards(deck),
                createPlayers(deck, "pobi"),
                deck
        );

        blackjackRound.stay(new Name("pobi"));
        blackjackRound.playDealerTurn();

        ProfitRate profitRate = blackjackRound.profitRateOf(new Name("pobi"));

        assertEquals(profitRate, ProfitRate.WIN);
    }

    @DisplayName("정산 시 플레이어와 딜러의 실제 수익 금액을 조회할 수 있다.")
    @Test
    void profitMoney() {
        Deck deck = Deck.from(
                createCard(Denomination.TEN, Suit.SPADE),
                createCard(Denomination.SEVEN, Suit.HEART),
                createCard(Denomination.TEN, Suit.DIAMOND),
                createCard(Denomination.NINE, Suit.CLOVER)
        );
        BlackjackRound blackjackRound = new BlackjackRound(
                Dealer.drawInitialCards(deck),
                createPlayers(deck, "pobi"),
                deck
        );

        blackjackRound.stay(new Name("pobi"));
        blackjackRound.playDealerTurn();

        assertEquals(blackjackRound.profitMoneyOf(new Name("pobi")), new ProfitMoney(1_000));
        assertEquals(blackjackRound.dealerProfitMoney(), new ProfitMoney(-1_000));
    }

    private Players createPlayers(Deck deck, String... names) {
        return new Players(List.of(names).stream()
                .map(name -> createPlayer(name, deck))
                .toList());
    }

    private Player createPlayer(String name, Deck deck) {
        return Player.drawInitialCards(new Name(name), new BettingMoney(1_000), deck);
    }

    private Card createCard(Denomination denomination, Suit suit) {
        return new Card(denomination, suit);
    }
}
