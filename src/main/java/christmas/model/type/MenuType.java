package christmas.model.type;

public enum MenuType {
    APPETIZER(1),
    MAIN(2),
    DESSERT(3),
    BEVERAGE(4),;

    private final int sort;

    MenuType(int sort) {
        this.sort = sort;
    }

    public int getSort() {
        return sort;
    }
}
