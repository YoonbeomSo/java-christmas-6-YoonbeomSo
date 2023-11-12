package christmas.model.event.detail;

import christmas.model.order.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class GiftEventTest {

    @DisplayName("증정품 제공 금액(120,000원)이상 구매시 증정품(샴페인 1개)을 제공한다.")
    @Test
    void 증정품_제공_테스트() {
        GiftEvent giftEvent = new GiftEvent();
        Map<Menu, Integer> emptyGiftMap = giftEvent.getGiftMap(10000);
        Map<Menu, Integer> champagneGiftMap = giftEvent.getGiftMap(120000);

        Assertions.assertThat(emptyGiftMap.size()).isEqualTo(0);
        Assertions.assertThat(champagneGiftMap.size()).isEqualTo(1);

        champagneGiftMap.entrySet()
                .forEach(c -> {
                    Assertions.assertThat(c.getKey().getName()).isEqualTo(Menu.CHAMPAGNE.getName());
                    Assertions.assertThat(c.getValue()).isEqualTo(1);
                });
    }
}