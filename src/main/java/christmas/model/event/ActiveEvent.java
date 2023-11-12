package christmas.model.event;

import christmas.model.event.detail.*;

import java.util.List;
import java.util.stream.Collectors;

public enum ActiveEvent {

    CHRISTMAS_D_DAY_EVENT(true, new ChristmasDdayEvent()),
    WEEKDAY_EVENT(true, new WeekdayEvent()),
    WEEKEND_EVENT(true, new WeekendEvent()),
    SPECIAL_EVENT(true, new SpecialEvent()),
    GIFT_EVENT(true, new GiftEvent()),
    ;

    private final boolean isActive;
    private final Event event;

    ActiveEvent(boolean isActive, Event event) {
        this.isActive = isActive;
        this.event = event;
    }

    public static List<Event> findAllActiveEvent() {
        return List.of(ActiveEvent.values()).stream()
                .filter(ActiveEvent::isActive)
                .map(ActiveEvent::getEvent)
                .collect(Collectors.toList());
    }

    public boolean isActive() {
        return isActive;
    }

    public Event getEvent() {
        return event;
    }
}
