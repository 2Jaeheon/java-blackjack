package domain.participant;

import domain.ErrorMessage;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validateDuplicateName(players);
        this.players = List.copyOf(players);
    }

    public int size() {
        return players.size();
    }

    private void validateDuplicateName(List<Player> players) {
        Set<Name> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toSet());

        if (names.size() != players.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_PLAYER_NAME);
        }
    }
}
