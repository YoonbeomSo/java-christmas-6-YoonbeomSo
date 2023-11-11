package christmas;

import christmas.service.ChristmasService;

public class Application {
    public static void main(String[] args) {
        ChristmasService christmasService = new ChristmasService();
        christmasService.eventStart();
    }
}
