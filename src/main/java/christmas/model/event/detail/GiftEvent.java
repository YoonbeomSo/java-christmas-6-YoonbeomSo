package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.type.Menu;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GiftEvent extends Event {

    private static final String NAME = "증정 이벤트";
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate EMD_DATE = LocalDate.of(2023, 12, 31);
    private static final int STANDARD_AMOUNT = 120000;
    private static final Map<Menu, Integer> GIFT_MAP = Map.of(Menu.CHAMPAGNE, 1);

    @Override
    public boolean isValidEvent(Orders orders) {
        return periodCheck(orders.getDate()) && amountCheck(orders.calculateTotalAmount());
    }

    @Override
    public Integer getBenefitAmount(Orders orders) {
        if (!isValidEvent(orders)) {
            return 0;
        }
        return GIFT_MAP.entrySet().stream()
                .mapToInt(g -> g.getKey().getPrice() * g.getValue())
                .sum();
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Map<Menu, Integer> getGiftMap(int amount, LocalDate date) {
        if (periodCheck(date) && amountCheck(amount)) {
            return GIFT_MAP;
        }
        return new HashMap<>();
    }

    private boolean periodCheck(LocalDate date) {
        if (date.isBefore(START_DATE) || date.isAfter(EMD_DATE)) {
            return false;
        }
        return true;
    }

    private boolean amountCheck(int amount) {
        if (amount < STANDARD_AMOUNT) {
            return false;
        }
        return true;
    }


}
