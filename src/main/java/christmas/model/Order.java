package christmas.model;

public class Order {

    private final Menu menu;
    private final int day;

    public Order(Menu menu, int day) {
        this.menu = menu;
        this.day = day;
    }
}
