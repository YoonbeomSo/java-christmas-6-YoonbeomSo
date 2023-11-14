package christmas.model.order;

import christmas.model.order.type.Menu;

import static christmas.common.ErrorMessageType.*;

public class OrderMenu {

    private final Menu menu;
    private final int count;

    public OrderMenu(String strMenu) {
        String[] split = strMenu.split("-");
        validateSplit(split);
        this.menu = convertMenu(split[0].trim());
        this.count = convertCount(split[1].trim());
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }

    private void validateSplit(String[] split) {
        if (split.length != 2) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
    }

    private Menu convertMenu(String name) {
        return Menu.findByName(name);
    }

    private int convertCount(String count) {
        int result;
        try {
            result = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
        validateCount(result);
        return result;
    }

    private void validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
    }
}
