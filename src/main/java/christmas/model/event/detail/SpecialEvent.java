package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.List;

public class SpecialEvent extends Event {

    private static final String NAME = "특별 할인";
    private static final int BENEFIT_AMOUNT = 1000;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);
    private static final List<LocalDate> STAR_DATE_LIST = List.of(
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
        return BENEFIT_AMOUNT;
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

    private boolean starDateCheck(LocalDate date) {
        return STAR_DATE_LIST.contains(date);
    }
}
