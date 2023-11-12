package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Menu;
import christmas.model.order.Orders;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GiftEvent extends Event {
    private static final String NAME = "증정 이벤트";

    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);
    private final int STANDARD_AMOUNT = 120000;
    private final Map<Menu, Integer> giftMap = Map.of(Menu.CHAMPAGNE, 1);

    @Override
    public boolean isValidEvent(Orders orders) {
        return periodCheck(orders.getDate()) && amountCheck(orders.getTotalAmount());
    }

    @Override
    public Integer getBenefitAmount(Orders orders) {
        if(!isValidEvent(orders)){
            return 0;
        }
        return giftMap.entrySet().stream()
                .mapToInt(g -> g.getKey().getPrice() * g.getValue())
                .sum();
    }

    @Override
    public String getName() {
        return NAME;
    }

    public Map<Menu, Integer> getGiftMap(int amount, LocalDate date) {
        if (periodCheck(date) && amountCheck(amount)) {
            return giftMap;
        }
        return new HashMap<>();
    }

    private boolean periodCheck(LocalDate date) {
        if(date.isBefore(startDate) || date.isAfter(endDate)) {
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
