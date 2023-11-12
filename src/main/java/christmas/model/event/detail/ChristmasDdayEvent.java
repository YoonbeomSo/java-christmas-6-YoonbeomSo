package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDdayEvent extends Event {
    private static final String NAME = "크리스마스 디데이 할인";
    private static final int BENEFIT_AMOUNT = 1000;
    private static final int PLUS_BENEFIT_AMOUNT = 100;

    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 25);

    @Override
    public boolean isValidEvent(Orders orders) {
        return periodCheck(orders.getDate());
    }

    @Override
    public Integer getBenefitAmount(Orders orders) {
        if(!isValidEvent(orders)) {
            return 0;
        }
        return getTotalBenefitAmount(orders);
    }

    @Override
    public String getName() {
        return NAME;
    }

    private int getTotalBenefitAmount(Orders orders) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, orders.getDate());
        int intDayBetween = (int) daysBetween;
        int result = BENEFIT_AMOUNT + (intDayBetween * PLUS_BENEFIT_AMOUNT);
        return result;
    }

    private boolean periodCheck(LocalDate date) {
        if(date.isBefore(startDate) || date.isAfter(endDate)) {
            return false;
        }
        return true;
    }
}
