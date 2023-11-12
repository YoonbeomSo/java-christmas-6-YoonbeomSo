package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.List;

public class SpecialEvent extends Event {

    private static final String NAME = "특별 할인";
    private final int benefitAmount = 1000;
    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private final List<LocalDate> starDateList = List.of(
            LocalDate.of(2023, 12, 3),
            LocalDate.of(2023, 12, 10),
            LocalDate.of(2023, 12, 17),
            LocalDate.of(2023, 12, 24),
            LocalDate.of(2023, 12, 25),
            LocalDate.of(2023, 12, 30)
    );

    @Override
    public boolean isValidEvent(Orders orders) {
        return periodCheck(orders.getDate()) && starDateCheck(orders.getDate());
    }

    @Override
    public Integer getBenefitAmount(Orders orders) {
        if(!isValidEvent(orders)) {
            return 0;
        }
        return benefitAmount;
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

    private boolean starDateCheck(LocalDate date) {
        return starDateList.contains(date);
    }
}
