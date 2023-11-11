package christmas.common;

public enum ErrorMessageType {

    ERROR("[ERROR]"),
    ERROR_NOT_NUMBER("숫자를 입력해 주세요."),
    ERROR_INVALID_DATE("유효하지 않은 날짜입니다."),
    ;

    private final String message;

    ErrorMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
