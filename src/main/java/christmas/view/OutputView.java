package christmas.view;

import christmas.common.MessageType;
import christmas.model.Orders;

import java.time.LocalDate;
import java.util.List;

import static christmas.common.MessageType.*;

public class OutputView {
    public static void printHello() {
        System.out.println(HELLO_MESSAGE.getMessage());
    }

    public static void printBefitForeword(LocalDate date) {
        System.out.println(getBenefitForewordMessage(date));
        System.out.println();
    }

    public static void printMenu(Orders orders) {
        List<String> orderList = orders.getOrderList();
        System.out.println(MENU_TITLE.getMessage());
        orderList.forEach(o -> System.out.println(o));
        System.out.println();
    }

    public static void printOriginalAmount() {
    }

    public static void printFreeMenu() {
    }

    public static void printDiscountList() {
    }

    public static void printTotalDiscount() {
    }

    public static void printTotalAmount() {
    }

    public static void printEventBadge() {
    }

}
