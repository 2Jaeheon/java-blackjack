package domain.participant;

import domain.ErrorMessage;

public record Name(String value) {
    private static final int MAX_LENGTH = 5;
    private static final int HANGUL_START = 0xAC00;
    private static final int HANGUL_END = 0xD7A3;
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

    public Name {
        validateNullOrBlank(value);
        validateLength(value);
    }

    private void validateNullOrBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME);
        }
    }

    private void validateLength(String value) {
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.NAME_TOO_LONG);
        }
    }

    public String topicMarker() {
        char lastCharacter = value.charAt(value.length() - 1);
        if (isHangul(lastCharacter)) {
            return topicMarkerOfHangul(lastCharacter);
        }
        if (hasConsonantSound(lastCharacter)) {
            return "은";
        }
        return "는";
    }

    private boolean isHangul(char character) {
        return character >= HANGUL_START && character <= HANGUL_END;
    }

    private String topicMarkerOfHangul(char character) {
        if (hasBatchim(character)) {
            return "은";
        }
        return "는";
    }

    private boolean hasBatchim(char character) {
        return (character - HANGUL_START) % 28 != 0;
    }

    private boolean hasConsonantSound(char character) {
        return CONSONANTS.indexOf(Character.toLowerCase(character)) >= 0;
    }
}
