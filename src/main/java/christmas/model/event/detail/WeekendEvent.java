package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Orders;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static christmas.model.order.MenuType.MAIN;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

public class WeekendEvent extends Event {

    private static final String NAME = "주말 할인";
    private static final int benefitAmount = 2023;
    private static final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private static final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private static final List<DayOfWeek> dayOfWeekList = List.of(FRIDAY, SATURDAY);

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
                .filter(om -> om.getMenu().isMain(MAIN))
                .mapToInt(om -> om.getCount() * benefitAmount)
                .sum();
    }

    @Override
    public String getName() {
        return NAME;
    }

    private boolean periodCheck(LocalDate date) {
        if (date.isBefore(startDate) || date.isAfter(endDate)) {
            return false;
        }
        return true;
    }

    private boolean weekendCheck(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeekList.contains(dayOfWeek);
    }
}
