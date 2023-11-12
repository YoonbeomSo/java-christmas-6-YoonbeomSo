package christmas.model.order;

import christmas.model.event.Event;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static christmas.common.ErrorMessageType.*;
import static christmas.common.MessageType.*;

public class Orders {

    private static final int MAX_ORDER_COUNT = 20;

    private final List<OrderMenu> orderMenuList = new ArrayList<>();
    private final LocalDate date;
    private int benefitAmount;

    public Orders(List<OrderMenu> orderMenuList, LocalDate date) {
        validateDuplicate(orderMenuList);
        validateUserCaution(orderMenuList);
        this.orderMenuList.addAll(orderMenuList);
        this.date = date;
    }

    public List<String> getOrderList() {
        return orderMenuList.stream()
                .sorted(Comparator.comparingInt(om -> om.getMenu().getMenuType().getSort()))
                .map(om -> getMenuMessage(om.getMenu().getName(), om.getCount()))
                .collect(Collectors.toList());
    }

    public int getTotalAmount() {
        return orderMenuList.stream()
                .mapToInt(om -> om.getMenu().getPrice() * om.getCount())
                .sum();
    }

    public LocalDate getDate() {
        return date;
    }

    public List<OrderMenu> getOrderMenuList() {
        return orderMenuList;
    }

    private void validateDuplicate(List<OrderMenu> orderMenuList) {
        List<Menu> distinctList = orderMenuList.stream()
                .map(om -> om.getMenu())
                .distinct()
                .collect(Collectors.toList());
        if (distinctList.size() != orderMenuList.size()) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
    }

    private void validateUserCaution(List<OrderMenu> orderMenuList) {
        validateNotOnlyBeverage(orderMenuList);
        validateMaxOrderSize(orderMenuList);
    }

    private static void validateNotOnlyBeverage(List<OrderMenu> orderMenuList) {
        List<OrderMenu> beverageList = orderMenuList.stream()
                .filter(om -> Menu.isBeverage(om.getMenu()))
                .collect(Collectors.toList());
        if (beverageList.size() == orderMenuList.size()) {
            throw new IllegalArgumentException(ERROR_NOT_ONLY_BEVERAGE.getInputErrorMessage());
        }
    }

    private static void validateMaxOrderSize(List<OrderMenu> orderMenuList) {
        int countSum = orderMenuList.stream()
                .mapToInt(om -> om.getCount())
                .sum();
        if (countSum > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(ERROR_OVER_MAX_ORDER_SIZE.getInputErrorMessage());
        }
    }
}
