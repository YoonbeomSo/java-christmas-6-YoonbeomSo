package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

import static christmas.common.MessageType.*;
import static christmas.common.ErrorMessageType.*;

public class InputView {

    public static int readDate() {
        System.out.println(ASK_VISIT_DATE.getMessage());
        String input = Console.readLine();
        int date = convertDateStringToInt(input);
        return date;
    }

    public static List<String> readMenus() {
        System.out.println(ASK_MENU_AND_COUNT.getMessage());
        String input = Console.readLine();
        return splitInput(input, ",");
    }

    private static List<String> splitInput(String input, String regex) {
        List<String> stringMenulist = List.of(input.split(regex));
        return stringMenulist;
    }

    private static int convertDateStringToInt(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getInputErrorMessage());
        }
    }
}
