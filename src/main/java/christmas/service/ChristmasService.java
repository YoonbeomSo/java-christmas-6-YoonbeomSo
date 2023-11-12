package christmas.service;

import christmas.model.OrderMenu;
import christmas.model.Orders;
import christmas.model.type.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static christmas.common.ErrorMessageType.ERROR_INVALID_DATE;
import static christmas.model.type.EventDate.EVENT_MONTH;
import static christmas.model.type.EventDate.EVENT_YEAR;

public class ChristmasService {

    public void eventStart() {
        printHello();
        LocalDate date = getDate();
        Orders orders = getOrders(date);
        printBefitForeword(date);

        printMenus(orders);
        printOriginalAmount(null);

        printFreeMenus(null);
        printDiscountList(null);
        printTotalDiscount(null);
        printTotalAmount(null);
        printEventBadge(null);
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
            return buildOrders(InputView.readMenus(),date);
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

    private void printOriginalAmount(Menu menu) {
        OutputView.printOriginalAmount();
    }

    private void printFreeMenus(Orders orders) {
        OutputView.printFreeMenu();
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
