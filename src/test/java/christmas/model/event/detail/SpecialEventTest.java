package christmas.model.event.detail;


import christmas.model.event.Event;
import christmas.model.order.OrderMenu;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialEventTest {
    private List<OrderMenu> orderMenuList;
    private Event event;

    @BeforeEach
    void init() {
        orderMenuList = new ArrayList<>();
        orderMenuList.add(new OrderMenu("아이스크림-3"));

        event = new SpecialEvent();
    }

    @DisplayName("이벤트 기간에 해당하지 않으면 false 를 리턴 해야한다.")
    @Test
    public void 이벤트_기간_테스트() {
        Orders validOrder = new Orders(orderMenuList, LocalDate.of(2023, 12, 25));
        Orders invalidOrder = new Orders(orderMenuList, LocalDate.of(2023, 12, 21));

        assertThat(event.isValidEvent(validOrder)).isTrue();
        assertThat(event.isValidEvent(invalidOrder)).isFalse();
    }

    @DisplayName("이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인 한다.")
    @Test
    public void 이벤트_혜택_금액_테스트() {
        Orders orderDuringEvent = new Orders(orderMenuList, LocalDate.of(2023, 12, 25));

        int expectedBenefit = 1000;
        assertThat(event.getBenefitAmount(orderDuringEvent)).isEqualTo(expectedBenefit);
    }

    @DisplayName("이벤트 기간을 벗어난 경우 혜택 금액은 0원이다.")
    @Test
    public void 이벤트_혜택_금액_없음_테스트() {
        Orders orderBeforeEvent = new Orders(orderMenuList, LocalDate.of(2023, 12, 21));

        assertThat(event.getBenefitAmount(orderBeforeEvent)).isEqualTo(0);
    }
}