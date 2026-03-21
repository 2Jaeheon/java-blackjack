package participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import domain.participant.BettingMoney;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @DisplayName("Players는 여러 명의 Player를 가진다.")
    @Test
    void create() {
        Players players = new Players(List.of(
                createPlayer("pobi"),
                createPlayer("jason")
        ));

        assertThat(players.size()).isEqualTo(2);
    }

    @DisplayName("Players는 이름이 중복되면 생성할 수 없다.")
    @Test
    void create_Exception_WhenDuplicateName() {
        List<Player> players = List.of(
                createPlayer("pobi"),
                createPlayer("pobi")
        );

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Player createPlayer(String name) {
        return new Player(
                new Name(name),
                createCards(
                        new Card(Denomination.TEN, Suit.SPADE),
                        new Card(Denomination.NINE, Suit.HEART)
                ),
                new BettingMoney(1_000)
        );
    }

    private Cards createCards(Card... source) {
        Cards cards = new Cards();
        for (Card card : source) {
            cards.add(card);
        }
        return cards;
    }
}
