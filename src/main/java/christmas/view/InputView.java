package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.time.DateTimeException;
import java.time.LocalDate;

import static christmas.common.MessageType.*;
import static christmas.common.ErrorMessageType.*;
import static christmas.model.type.EventDateType.*;

public class InputView {

    public static int readDate() {
        try {
            System.out.println(ASK_VISIT_DATE.getMessage());
            String input = Console.readLine();
            int date = convertStringToInt(input);
            validateEventDate(date);
            return date;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return readDate();
        }
    }

    public static void readMenu() {

    }

    private static int convertStringToInt(String target) {
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER.getMessage());
        }
    }

    private static void validateEventDate(int date) {
        try {
            LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getMessage());
        }
    }
}
