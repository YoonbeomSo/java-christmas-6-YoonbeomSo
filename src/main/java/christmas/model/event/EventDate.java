package christmas.model.event;

import java.time.DateTimeException;
import java.time.LocalDate;

import static christmas.common.ErrorMessageType.ERROR_INVALID_DATE;

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

    public static LocalDate findLocalDateByDay(int day) {
        try {
            return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getInputErrorMessage());
        }
    }
}
