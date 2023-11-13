package christmas.model.order;

import christmas.model.event.Event;
import christmas.model.event.detail.GiftEvent;
import christmas.model.event.detail.WeekdayEvent;
import christmas.model.event.detail.WeekendEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class OrdersTest {

    @DisplayName("옳바른 주문 목록을 입력 하면 Orders 인스턴스가 정상적으로 생성된다.")
    @Test
    void 생성_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("해산물파스타-2"),
                new OrderMenu("레드와인-1")
        );
        Orders orders = new Orders(orderMenuList, LocalDate.now());

        assertThat(orders).isNotNull();
        assertThat(2).isEqualTo(orders.getOrderList().size());
    }

    @DisplayName("중복 메뉴가 있는 경우 IllegalArgumentException 을 발생 시킨다.")
    @Test
    void 중복_메뉴_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("해산물파스타-2"),
                new OrderMenu("해산물파스타-2")
        );
        assertThatThrownBy(() -> new Orders(orderMenuList, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문한 경우 IllegalArgumentException 을 발생 시킨다.")
    @Test
    void 음료_단독_주문_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("제로콜라-1"),
                new OrderMenu("레드와인-2")
        );

        assertThatThrownBy(() -> new Orders(orderMenuList, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("최대 주문 수량 초과 시 IllegalArgumentException 을 발생 시킨다.")
    @Test
    void 최대_주문_수량_초과_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("해산물파스타-17"),
                new OrderMenu("레드와인-4")
        );

        assertThatThrownBy(() -> new Orders(orderMenuList, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 금액 x 구매수량을 모두 합한 값과 같아야 한다.")
    @Test
    void 총_금액_계산_테스트() {
        String menu1 = Menu.SEAFOOD_PASTA.getName();
        int count1 = 2;
        int price1 = Menu.SEAFOOD_PASTA.getPrice() * count1;

        String menu2 = Menu.CHAMPAGNE.getName();
        int count2 = 2;
        int price2 = Menu.CHAMPAGNE.getPrice() * count2;

        int totalPrice = price1 + price2;

        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu(menu1 + "-" + count1),
                new OrderMenu(menu2 + "-" + count2)
        );

        Orders orders = new Orders(orderMenuList, LocalDate.now());

        int totalAmount = orders.calculateTotalAmount();
        assertThat(totalPrice).isEqualTo(totalAmount);
    }

    @DisplayName("주중은 메인 1개에 2023원, 주말은 디저트 1개에 2023원 의 혜택 금액이 지급 되어야 한다.")
    @Test
    void 혜택_금액_계산_테스트() {
        String menu1 = Menu.SEAFOOD_PASTA.getName();
        int count1 = 1;
        String menu2 = Menu.CHOCOLATE_CAKE.getName();
        int count2 = 1;

        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu(menu1 + "-" + count1),
                new OrderMenu(menu2 + "-" + count2));

        List<Event> events = List.of(new WeekdayEvent(), new WeekendEvent());
        Orders weekendOrders = new Orders(orderMenuList, LocalDate.of(2023, 12, 25));
        Orders weekdayOrders = new Orders(orderMenuList, LocalDate.of(2023, 12, 24));

        int weekendBenefitAmount = weekendOrders.calculateBenefitAmount(events);
        int weekdayBenefitAmount = weekdayOrders.calculateBenefitAmount(events);

        assertThat(weekendBenefitAmount).isEqualTo(2023);
        assertThat(weekdayBenefitAmount).isEqualTo(2023);
    }

    @DisplayName("최종 지불 금액 = 구매금액 - (총 혜택 금액 - 사은품 혜택 금액)")
    @Test
    void 최종_지불_금액_계산_테스트() {
        String menu1 = Menu.SEAFOOD_PASTA.getName();
        int count1 = 1;
        String menu2 = Menu.CHOCOLATE_CAKE.getName();
        int count2 = 2;

        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu(menu1 + "-" + count1),
                new OrderMenu(menu2 + "-" + count2));

        List<Event> events = List.of(new WeekdayEvent(), new WeekendEvent());
        Orders orders = new Orders(orderMenuList, LocalDate.of(2023, 12, 25));
        GiftEvent giftEvent = new GiftEvent();

        int totalAmount = orders.calculateTotalAmount();
        int benefitAmount = orders.calculateBenefitAmount(events);
        int giftEventBenefitAmount = giftEvent != null ? giftEvent.getBenefitAmount(orders) : 0;
        int expectedFinalAmount = totalAmount - (benefitAmount - giftEventBenefitAmount);

        int actualFinalAmount = orders.calculateResultAmount(events, giftEvent);
        assertThat(actualFinalAmount).isEqualTo(expectedFinalAmount);
    }

    @DisplayName("메뉴타입, 혜택 금액에 따라 혜택 금액이 지급 되어야 한다.")
    @Test
    void 이벤트_별_혜택_금액_계산_테스트() {
        String menu1 = Menu.SEAFOOD_PASTA.getName();
        int count1 = 1;
        String menu2 = Menu.CHOCOLATE_CAKE.getName();
        int count2 = 2;
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu(menu1 + "-" + count1),
                new OrderMenu(menu2 + "-" + count2));

        MenuType menuType = MenuType.DESSERT;
        Orders orders = new Orders(orderMenuList, LocalDate.of(2023, 12, 25));
        int amount = 1000;

        int actualEventBenefitAmount = orders.calculateEventBenefitAmount(1000, menuType);
        assertThat(actualEventBenefitAmount).isEqualTo(amount * 2);
    }

}