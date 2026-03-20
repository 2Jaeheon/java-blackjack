package domain;

public record Card(Denomination denomination, Suit suit) {
    public boolean isAce() {
        return this.denomination == Denomination.ACE;
    }

    public int getScore() {
        return denomination.getScore();
    }
}
