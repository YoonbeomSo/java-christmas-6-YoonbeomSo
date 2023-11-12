package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static christmas.model.order.MenuType.*;
import static java.time.DayOfWeek.*;

public class WeekdayEvent extends Event {
    private static final String NAME = "평일 할인";
    private final int benefitAmount = 2023;

    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private final List<DayOfWeek> dayOfWeekList = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY);

    @Override
    public boolean isValidEvent(Orders orders) {
        return periodCheck(orders.getDate()) && weekendCheck(orders.getDate());
    }

    @Override
    public Integer getBenefitAmount(Orders orders) {
        if (!isValidEvent(orders)) {
            return 0;
        }
        return orders.getOrderMenuList().stream()
                .filter(om -> om.getMenu().isDessert(DESSERT))
                .mapToInt(om -> om.getCount() * benefitAmount)
                .sum();
    }

    @Override
    public String getName() {
        return NAME;
    }

    private boolean periodCheck(LocalDate date) {
        if(date.isBefore(startDate) || date.isAfter(endDate)) {
            return false;
        }
        return true;
    }

    private boolean weekendCheck(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeekList.contains(dayOfWeek);
    }
}
