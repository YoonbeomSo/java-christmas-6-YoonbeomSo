package christmas.model;

import christmas.model.type.Menu;

import static christmas.common.ErrorMessageType.*;

public class OrderMenu {

    private final Menu menu;
    private final int count;

    public OrderMenu(String stringMenu) {
        String[] split = stringMenu.split("-");
        validateSplit(split);
        this.menu = convertMenu(split[0]);
        this.count = convertCount(split[1]);
    }

    private void validateSplit(String[] split) {
        if(split.length != 2) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
    }

    private Menu convertMenu(String name) {
        return Menu.findByName(name);
    }

    private int convertCount(String count) {
        try {
            int result = Integer.parseInt(count);
            validateCount(result);
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
    }

    private void validateCount(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(ERROR_INVALID_MENU.getInputErrorMessage());
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public int getCount() {
        return count;
    }
}
