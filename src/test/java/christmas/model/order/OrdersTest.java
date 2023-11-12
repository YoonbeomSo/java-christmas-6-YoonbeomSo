package christmas.model.order;

import christmas.model.order.Menu;
import christmas.model.order.OrderMenu;
import christmas.model.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


class OrdersTest {

    @Test
    @DisplayName("옳바른 주문 목록을 입력 하면 Orders 인스턴스가 정상적으로 생성된다.")
    void 생성_테스트(){
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("해산물파스타-2"),
                new OrderMenu("레드와인-1")
        );
        Orders orders = new Orders(orderMenuList, LocalDate.now());

        assertThat(orders).isNotNull();
        assertThat(2).isEqualTo(orders.getOrderList().size());
    }

    @Test
    @DisplayName("중복 메뉴가 있는 경우 IllegalArgumentException 을 발생 시킨다.")
    void 중복_메뉴_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("해산물파스타-2"),
                new OrderMenu("해산물파스타-2")
        );
        assertThatThrownBy(() -> new Orders(orderMenuList, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음료만 주문한 경우 IllegalArgumentException 을 발생 시킨다.")
    void 음료_단독_주문_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("제로콜라-1"),
                new OrderMenu("레드와인-2")
        );

        assertThatThrownBy(() -> new Orders(orderMenuList, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최대 주문 수량 초과 시 IllegalArgumentException 을 발생 시킨다.")
    void 최대_주문_수량_초과_테스트() {
        List<OrderMenu> orderMenuList = Arrays.asList(
                new OrderMenu("해산물파스타-17"),
                new OrderMenu("레드와인-4")
        );

        assertThatThrownBy(() -> new Orders(orderMenuList, LocalDate.now()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("할인되지 않은 총 금액을 계산한다")
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

        int totalAmount = orders.getTotalAmount();
        assertThat(totalPrice).isEqualTo(totalAmount);
    }
}