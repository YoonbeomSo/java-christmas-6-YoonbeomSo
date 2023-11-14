package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static christmas.model.order.type.MenuType.*;
import static java.time.DayOfWeek.*;

public class WeekdayEvent extends Event {

    private static final String NAME = "평일 할인";
    private static final int BENEFIT_AMOUNT = 2023;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);
    private static final List<DayOfWeek> DAY_OF_WEEKS = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);

    @Override
    public boolean isValidEvent(Orders orders) {
        return periodCheck(orders.getDate()) && weekendCheck(orders.getDate());
    }

    @Override
    public Integer getBenefitAmount(Orders orders) {
        if (!isValidEvent(orders)) {
            return 0;
        }
        return orders.calculateEventBenefitAmount(BENEFIT_AMOUNT, DESSERT);
    }

    @Override
    public String getName() {
        return NAME;
    }

    private boolean periodCheck(LocalDate date) {
        if (date.isBefore(START_DATE) || date.isAfter(END_DATE)) {
            return false;
        }
        return true;
    }

    private boolean weekendCheck(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return DAY_OF_WEEKS.contains(dayOfWeek);
    }
}
