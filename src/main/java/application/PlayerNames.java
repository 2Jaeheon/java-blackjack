package application;

import domain.ErrorMessage;
import domain.participant.Name;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerNames implements Iterable<Name> {
    private final List<Name> values;

    public PlayerNames(List<Name> values) {
        validateDuplicate(values);
        this.values = List.copyOf(values);
    }

    public static PlayerNames from(String value) {
        return new PlayerNames(split(value));
    }

    public String joinedValues() {
        return values.stream()
                .map(Name::value)
                .collect(Collectors.joining(", "));
    }

    @Override
    public Iterator<Name> iterator() {
        return values.iterator();
    }

    private static List<Name> split(String value) {
        return List.of(value.split(",")).stream()
                .map(String::trim)
                .map(Name::new)
                .toList();
    }

    private void validateDuplicate(List<Name> values) {
        Set<Name> names = Set.copyOf(values);
        if (names.size() != values.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_PLAYER_NAME);
        }
    }
}
