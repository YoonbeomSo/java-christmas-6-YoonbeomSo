package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Menu;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GiftEvent extends Event {

    private final int STANDARD_AMOUNT = 120000;
    private final Map<Menu, Integer> giftMap = Map.of(Menu.CHAMPAGNE, 1);
    private final LocalDate startDate = LocalDate.of(2023, 12, 1);
    private final LocalDate endDate = LocalDate.of(2023, 12, 31);


    public Map<Menu, Integer> getGiftMap(int amount, LocalDate date) {
        if (amount >= STANDARD_AMOUNT && periodCheck(date)) {
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
}
