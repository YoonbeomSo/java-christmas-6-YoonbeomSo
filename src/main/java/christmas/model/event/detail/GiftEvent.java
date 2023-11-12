package christmas.model.event.detail;

import christmas.model.event.Event;
import christmas.model.order.Menu;

import java.util.HashMap;
import java.util.Map;

public class GiftEvent extends Event {

    private final int STANDARD_AMOUNT = 120000;
    private final Map<Menu, Integer> giftMap = Map.of(Menu.CHAMPAGNE, 1);

    public Map<Menu, Integer> getGiftMap(int amount) {
        if (amount >= STANDARD_AMOUNT) {
            return giftMap;
        }
        return new HashMap<>();
    }
}
