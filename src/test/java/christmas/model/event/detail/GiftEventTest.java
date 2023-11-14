package christmas.model.event.detail;

import christmas.model.order.type.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

class GiftEventTest {

    @DisplayName("증정품 제공 금액(120,000원)이상 구매시 증정품(샴페인 1개)을 제공한다.")
    @Test
    void 증정품_제공_금액_테스트() {
        GiftEvent giftEvent = new GiftEvent();
        LocalDate date = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> emptyGiftMap = giftEvent.getGiftMap(10000, date);
        Map<Menu, Integer> champagneGiftMap = giftEvent.getGiftMap(120000, date);

        Assertions.assertThat(emptyGiftMap.size()).isEqualTo(0);
        Assertions.assertThat(champagneGiftMap.size()).isEqualTo(1);

        champagneGiftMap.entrySet()
                .forEach(c -> {
                    Assertions.assertThat(c.getKey().getName()).isEqualTo(Menu.CHAMPAGNE.getName());
                    Assertions.assertThat(c.getValue()).isEqualTo(1);
                });
    }

    @DisplayName("이벤트 기간에 해당되지 않으면 증정품을 제공하지 않는다.")
    @Test
    void 증정품_제공_날짜_테스트() {
        GiftEvent giftEvent = new GiftEvent();
        LocalDate date1 = LocalDate.of(2023, 11, 30);
        LocalDate date2 = LocalDate.of(2023, 12, 1);
        Map<Menu, Integer> emptyGiftMap = giftEvent.getGiftMap(120000, date1);
        Map<Menu, Integer> champagneGiftMap = giftEvent.getGiftMap(120000, date2);

        Assertions.assertThat(emptyGiftMap.size()).isEqualTo(0);
        Assertions.assertThat(champagneGiftMap.size()).isEqualTo(1);
    }

}