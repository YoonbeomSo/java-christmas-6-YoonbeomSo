package christmas.model.type;

public enum EventDateType {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    ;

    private final int value;

    EventDateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
