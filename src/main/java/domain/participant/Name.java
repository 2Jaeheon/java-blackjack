package domain.participant;

import domain.ErrorMessage;

public record Name(String value) {
    private static final int MAX_LENGTH = 5;

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
}
