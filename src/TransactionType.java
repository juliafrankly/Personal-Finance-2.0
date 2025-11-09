public enum TransactionType {
    INCOME("Inkomst"),
    EXPENSE("Utgift");

    private final String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static TransactionType fromString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Transaktionstyp kan inte vara null");
        }

        for (TransactionType type : values()) {
            if (type.displayName.equalsIgnoreCase(input) || type.name().equalsIgnoreCase(input)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Ogiltig transaktionstyp: " + input);
    }
}