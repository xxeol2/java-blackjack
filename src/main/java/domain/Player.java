package domain;

public class Player {

    private static final int NAME_MAX_LENGTH = 5;

    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        validateBlank(name);
        validateLength(name);
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateLength(final String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException();
        }
    }
}