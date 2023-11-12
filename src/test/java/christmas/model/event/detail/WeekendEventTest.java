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

class WeekendEventTest {

    private List<OrderMenu> orderMenuList;
    private Event event;

    @BeforeEach
    void init() {
        orderMenuList = new ArrayList<>();
        orderMenuList.add(new OrderMenu("해산물파스타-3"));

        event = new WeekendEvent();
    }

    @DisplayName("이벤트 기간에 해당하지 않으면 false 를 리턴 해야한다.")
    @Test
    public void 이벤트_기간_테스트() {
        Orders validOrder = new Orders(orderMenuList, LocalDate.of(2023, 12, 23));
        Orders invalidOrder = new Orders(orderMenuList, LocalDate.of(2023, 11, 30));

        assertThat(event.isValidEvent(validOrder)).isTrue();
        assertThat(event.isValidEvent(invalidOrder)).isFalse();
    }

    @DisplayName("주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인 한다.")
    @Test
    public void 이벤트_혜택_금액_테스트() {
        Orders orderDuringEvent = new Orders(orderMenuList, LocalDate.of(2023, 12, 23));

        int expectedBenefit = 2023 * 3;
        assertThat(event.getBenefitAmount(orderDuringEvent)).isEqualTo(expectedBenefit);
    }

    @DisplayName("이벤트 기간을 벗어난 경우 혜택 금액은 0원이다.")
    @Test
    public void 이벤트_혜택_금액_없음_테스트() {
        Orders orderBeforeEvent = new Orders(orderMenuList, LocalDate.of(2023, 11, 30));

        assertThat(event.getBenefitAmount(orderBeforeEvent)).isEqualTo(0);
    }
}