package christmas.service;

import christmas.model.Menu;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasService {

    public void eventStart() {
        int day = getDay();
        Menu menu = getMenu();

        printMenus(menu);
        printOriginalAmount(menu);

        Order order = new Order(menu, day);
        printFreeMenus(order);
        printDiscountList(order);
        printTotalDiscount(order);
        printTotalAmount(order);
        printEventBadge(order);
    }

    private int getDay() {
        OutputView.printHello();
        InputView.readDate();
        return 0;
    }

    private Menu getMenu() {
        InputView.readMenu();
        return null;
    }

    private void printMenus(Menu menu) {
        OutputView.printMenu();
    }

    private void printOriginalAmount(Menu menu) {
        OutputView.printOriginalAmount();
    }

    private void printFreeMenus(Order order) {
        OutputView.printFreeMenu();
    }

    private void printDiscountList(Order order) {
        OutputView.printDiscountList();
    }

    private void printTotalDiscount(Order order) {
        OutputView.printTotalDiscount();
    }

    private void printTotalAmount(Order order) {
        OutputView.printTotalAmount();
    }

    private void printEventBadge(Order order) {
        OutputView.printEventBadge();
    }



}