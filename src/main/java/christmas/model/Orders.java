package christmas.model;

import christmas.model.type.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static christmas.common.ErrorMessageType.*;
import static christmas.common.MessageType.*;

public class Orders {

    private static final int MAX_ORDER_COUNT = 20;

    private final List<OrderMenu> orderMenuList = new ArrayList<>();
    private final LocalDate date;

    public Orders(List<OrderMenu> orderMenuList, LocalDate date) {
        validateDuplicate(orderMenuList);
        validateUserCaution(orderMenuList);
        this.orderMenuList.addAll(orderMenuList);
        this.date = date;
    }

    private void validateDuplicate(List<OrderMenu> orderMenuList) {
        List<OrderMenu> distinctList = orderMenuList.stream()
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

    public List<String> getOrderList() {
        return orderMenuList.stream()
                .sorted(Comparator.comparingInt(OrderMenu::getCount).reversed())
                .map(om -> getMenuMessage(om.getMenu().getName(), om.getCount()))
                .collect(Collectors.toList());
    }

}
