package christmas.model;

import christmas.model.type.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderMenuTest {


    @Test
    @DisplayName("정상적인 메뉴와 카운트 입력 시 OrderMenu 인스턴스트 정상적으로 생성된다.")
    void 생성_테스트() {
        // 주어진 메뉴 이름과 카운트가 유효한 경우
        String input = "해산물파스타-2";
        OrderMenu orderMenu = new OrderMenu(input);

        assertThat(orderMenu).isNotNull();
        assertThat(Menu.SEAFOOD_PASTA.getName()).isEqualTo(orderMenu.getMenu().getName());
        assertThat(2).isEqualTo(orderMenu.getCount());
    }

    @Test
    @DisplayName("잘못된 형식의 메뉴 입력 시 IllegalArgumentException 발생한다.")
    void 잘못된_메뉴_테스트() {
        String input = "잘못된메뉴";
        assertThatThrownBy(() -> new OrderMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 카운트 입력 시 IllegalArgumentException 발생한다.")
    void 잘못된_카운트_검증() {
        String input1 = "해산물파스타-0";
        String input2 = "해산물파스타--1";
        assertThatThrownBy(() -> new OrderMenu(input1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new OrderMenu(input2)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("존재하지 않는 메뉴 이름 입력 시 IllegalArgumentException 발생한다.")
    void 존재하지_않는_메뉴_검증() {
        String input = "없는메뉴-1";
        assertThatThrownBy(() -> new OrderMenu(input)).isInstanceOf(IllegalArgumentException.class);
    }

}