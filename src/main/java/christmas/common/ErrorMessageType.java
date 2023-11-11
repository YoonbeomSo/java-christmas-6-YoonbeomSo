package christmas.common;

public enum ErrorMessageType {

    ERROR("[ERROR] "),
    ERROR_INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ERROR_INVALID_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),

    ERROR_NOT_ONLY_BEVERAGE("음료만 주문 시, 주문할 수 없습니다. 다시 입력해 주세요."),
    ERROR_OVER_MAX_ORDER_SIZE("메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."),
    ;

    private final String message;

    ErrorMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getInputErrorMessage() {
        return ERROR.getMessage() + message;
    }
}
