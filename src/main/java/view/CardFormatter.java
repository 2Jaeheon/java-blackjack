package view;

import domain.cards.Card;
import domain.cards.Cards;
import domain.cards.Denomination;
import domain.cards.Suit;
import java.util.stream.Collectors;

public class CardFormatter {
    private static final String DELIMITER = ", ";

    public String format(Card card) {
        return format(card.denomination()) + format(card.suit());
    }

    public String format(Cards cards) {
        return cards.asList().stream()
                .map(this::format)
                .collect(Collectors.joining(DELIMITER));
    }

    private String format(Denomination denomination) {
        return switch (denomination) {
            case ACE -> "A";
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case JACK -> "J";
            case QUEEN -> "Q";
            case KING -> "K";
        };
    }

    private String format(Suit suit) {
        return switch (suit) {
            case SPADE -> "스페이드";
            case HEART -> "하트";
            case DIAMOND -> "다이아몬드";
            case CLOVER -> "클로버";
        };
    }
}
