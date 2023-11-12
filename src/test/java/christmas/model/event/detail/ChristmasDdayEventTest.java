package christmas.model.event.detail;

import christmas.model.order.Menu;
import christmas.model.order.OrderMenu;
import christmas.model.order.Orders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDdayEventTest {

    private List<OrderMenu> orderMenuList;

    @BeforeEach
    void init() {
        orderMenuList = new ArrayList<>();
        orderMenuList.add(new OrderMenu("해산물파스타-3"));
    }

    @DisplayName("이벤트 기간에 해당하지 않으면 false 를 리턴 해야한다.")
    @Test
    public void 이벤트_기간_테스트() {
        ChristmasDdayEvent event = new ChristmasDdayEvent();
        Orders validOrder = new Orders(orderMenuList, LocalDate.of(2023, 12, 10));
        Orders invalidOrder = new Orders(orderMenuList, LocalDate.of(2023, 11, 30));

        assertThat(event.isValidEvent(validOrder)).isTrue();
        assertThat(event.isValidEvent(invalidOrder)).isFalse();
    }

    @DisplayName("기본금액(1000원) + (12월1일 이후 결과 일수 * 추가금액(100월) 지급 되어야 한다.)")
    @Test
    public void 이벤트_혜택_금액_테스트() {
        ChristmasDdayEvent event = new ChristmasDdayEvent();
        Orders orderDuringEvent = new Orders(orderMenuList, LocalDate.of(2023, 12, 10));

        int expectedBenefit = 1000 + 9 * 100;
        assertThat(event.getBenefitAmount(orderDuringEvent)).isEqualTo(expectedBenefit);
    }

    @DisplayName("이벤트 기간을 벗어난 경우 혜택 금액은 0원 이다.")
    @Test
    public void 이벤트_혜택_금액_없음_테스트() {
        ChristmasDdayEvent event = new ChristmasDdayEvent();
        Orders orderBeforeEvent = new Orders(orderMenuList, LocalDate.of(2023, 11, 30));

        assertThat(event.getBenefitAmount(orderBeforeEvent)).isEqualTo(0);
    }
}