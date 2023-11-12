package christmas.model.event;

public enum EventDate {
    EVENT_YEAR(2023),
    EVENT_MONTH(12),
    ;

    private final int value;

    EventDate(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
