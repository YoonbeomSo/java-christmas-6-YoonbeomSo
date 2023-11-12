package christmas.view;

import christmas.model.event.Event;
import christmas.model.event.EventBadge;
import christmas.model.order.Orders;
import christmas.model.order.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
        System.out.println(MENU_TITLE.getMessage());
        List<String> orderList = orders.getOrderList();
        orderList.forEach(o -> System.out.println(o));
        System.out.println();
    }

    public static void printOriginalAmount(int totalAmount) {
        System.out.println(TOTAL_AMOUNT_TITLE.getMessage());
        System.out.println(getAmountMessage(totalAmount));
        System.out.println();
    }

    public static void printFreeMenu(Map<Menu, Integer> giftMap) {
        System.out.println(GIFT_TITLE.getMessage());
        if (giftMap.isEmpty()) {
            System.out.println(EMPTY.getMessage());
        }
        giftMap.entrySet().forEach(
                g -> System.out.println(getMenuMessage(g.getKey().getName() ,g.getValue()))
        );
        System.out.println();
    }

    public static void printBenefitList(Map<Event, Integer> benefitMap) {
        System.out.println(BENEFIT_TITLE.getMessage());
        if (benefitMap.isEmpty()) {
            System.out.println(EMPTY.getMessage());
        }
        benefitMap.entrySet().forEach(
                b -> System.out.println(getBenefitMessage(b.getKey().getName(), b.getValue()))
        );
        System.out.println();
    }

    public static void printTotalBenefit(int totalBenefit) {
        System.out.println(TOTAL_BENEFIT_TITLE.getMessage());
        if (totalBenefit != 0) {
            totalBenefit *= -1 ;
        }
        System.out.println(getAmountMessage(totalBenefit));
        System.out.println();
    }

    public static void printResultAmount(int resultAmount) {
        System.out.println(RESULT_AMOUNT_TITLE.getMessage());
        System.out.println(getAmountMessage(resultAmount));
        System.out.println();
    }

    public static void printEventBadge(EventBadge eventBadge) {
        System.out.println(EVENT_BADGE_TITLE.getMessage());
        if (eventBadge == null) {
            System.out.println(EMPTY.getMessage());
            return;
        }
        System.out.println(eventBadge.getName());
    }

}
