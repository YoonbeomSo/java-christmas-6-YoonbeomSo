package christmas.service;

import christmas.model.event.Event;
import christmas.model.order.OrderMenu;
import christmas.model.order.Orders;
import christmas.model.event.ActiveEvent;
import christmas.model.event.detail.GiftEvent;
import christmas.model.order.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static christmas.common.ErrorMessageType.ERROR_INVALID_DATE;
import static christmas.model.event.EventDate.EVENT_MONTH;
import static christmas.model.event.EventDate.EVENT_YEAR;

public class ChristmasService {

    public void eventStart() {
        printHello();
        LocalDate date = getDate();
        Orders orders = getOrders(date);
        printBefitForeword(date);

        printMenus(orders);
        printOriginalAmount(orders);

        List<Event> events = getActiveEvent();

        printGift(getGift(events), orders.getTotalAmount());
        printDiscountList(null);
        printTotalDiscount(null);
        printTotalAmount(null);
        printEventBadge(null);
    }

    private List<Event> getActiveEvent() {
        return ActiveEvent.findAllActiveEvent();
    }

    private static void printHello() {
        OutputView.printHello();
    }

    private LocalDate getDate() {
        try {
            return getLocalDate(InputView.readDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDate();
        }
    }

    private Orders getOrders(LocalDate date) {
        try {
            return buildOrders(InputView.readMenus(), date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getOrders(date);
        }
    }

    private void printBefitForeword(LocalDate date) {
        OutputView.printBefitForeword(date);
    }

    private void printMenus(Orders orders) {
        OutputView.printMenu(orders);
    }

    private void printOriginalAmount(Orders orders) {
        int totalAmount = orders.getTotalAmount();
        OutputView.printOriginalAmount(totalAmount);
    }

    private GiftEvent getGift(List<Event> events) {
        return events.stream()
                .filter(e -> e instanceof GiftEvent)
                .map(e -> (GiftEvent) e)
                .findFirst()
                .orElse(null);
    }

    private void printGift(GiftEvent event, int totalAmount) {
        Map<Menu, Integer> giftMap = event.getGiftMap(totalAmount);
        OutputView.printFreeMenu(giftMap);
    }

    private void printDiscountList(Orders orders) {
        OutputView.printDiscountList();
    }

    private void printTotalDiscount(Orders orders) {
        OutputView.printTotalDiscount();
    }

    private void printTotalAmount(Orders orders) {
        OutputView.printTotalAmount();
    }

    private void printEventBadge(Orders orders) {
        OutputView.printEventBadge();
    }

    private static LocalDate getLocalDate(int date) {
        try {
            return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getInputErrorMessage());
        }
    }

    private static Orders buildOrders(List<String> menuList, LocalDate date) {
        List<OrderMenu> orderMenuList = new ArrayList<>();
        menuList.forEach(m -> orderMenuList.add(new OrderMenu(m)));
        return new Orders(orderMenuList, date);
    }
}
