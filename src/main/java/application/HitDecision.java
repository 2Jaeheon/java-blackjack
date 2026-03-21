package application;

import domain.ErrorMessage;

public enum HitDecision {
    HIT("y"),
    STAY("n");

    private final String value;

    HitDecision(String value) {
        this.value = value;
    }

    public static HitDecision from(String value) {
        String normalizedValue = value.trim();
        if (HIT.value.equalsIgnoreCase(normalizedValue)) {
            return HIT;
        }
        if (STAY.value.equalsIgnoreCase(normalizedValue)) {
            return STAY;
        }
        throw new IllegalArgumentException(ErrorMessage.INVALID_HIT_DECISION);
    }

    public boolean isHit() {
        return this == HIT;
    }
}
