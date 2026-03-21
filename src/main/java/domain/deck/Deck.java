package domain.deck;

import domain.ErrorMessage;
import domain.cards.Card;
import domain.cards.Denomination;
import domain.cards.Suit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public static Deck shuffled() {
        return new Deck(createShuffledCards());
    }

    public static Deck from(Card... cards) {
        return new Deck(new ArrayDeque<>(Arrays.asList(cards)));
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(ErrorMessage.EMPTY_DECK);
        }
        return cards.pop();
    }

    public int size() {
        return cards.size();
    }

    private static Deque<Card> createShuffledCards() {
        List<Card> generatedCards = new ArrayList<>();

        Arrays.stream(Denomination.values()).forEach(denomination -> {
            for (Suit suit : Suit.values()) {
                generatedCards.add(new Card(denomination, suit));
            }
        });

        Collections.shuffle(generatedCards);
        return new ArrayDeque<>(generatedCards);
    }
}
