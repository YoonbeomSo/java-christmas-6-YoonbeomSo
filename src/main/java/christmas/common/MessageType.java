package christmas.common;

public enum MessageType {

    HELLO_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ASK_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ASK_MENU_AND_COUNT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    MENU_TITLE("<주문 메뉴>"),
    MENU_FORMAT("%s %d개")
    ;

    private final String message;

    MessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public static String getMenuMessage(String name, int count) {
        return String.format(MENU_FORMAT.getMessage(), name, count);
    }

}
