package christmas.service;

import christmas.model.event.Event;
import christmas.model.order.OrderMenu;
import christmas.model.order.Orders;
import christmas.model.event.ActiveEvent;
import christmas.model.event.detail.GiftEvent;
import christmas.model.order.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static christmas.common.ErrorMessageType.ERROR_INVALID_DATE;
import static christmas.model.event.EventDate.EVENT_MONTH;
import static christmas.model.event.EventDate.EVENT_YEAR;

public class ChristmasService {

    public void eventStart() {
        Orders orders = reservation();

        printBefitForeword(orders.getDate());
        printMenus(orders);
        printOriginalAmount(orders);

        List<Event> events = getValidEventList(orders);

        printGift(getGift(events), orders);
        printBenefitList(events, orders);
        printTotalBenefit(events, orders);
        printTotalAmount(null);
        printEventBadge(null);
    }

    private Orders reservation() {
        printHello();
        LocalDate date = getDate();
        return getOrders(date);
    }

    private static void printHello() {
        OutputView.printHello();
    }

    private LocalDate getDate() {
        try {
            return getLocalDate(InputView.readDate());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getDate();
        }
    }

    private Orders getOrders(LocalDate date) {
        try {
            return buildOrders(InputView.readMenus(), date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getOrders(date);
        }
    }

    private void printBefitForeword(LocalDate date) {
        OutputView.printBefitForeword(date);
    }

    private void printMenus(Orders orders) {
        OutputView.printMenu(orders);
    }

    private void printOriginalAmount(Orders orders) {
        int totalAmount = orders.getTotalAmount();
        OutputView.printOriginalAmount(totalAmount);
    }

    private List<Event> getValidEventList(Orders orders) {
        return ActiveEvent.findAllActiveEvent().stream()
                .filter(e -> e.isValidEvent(orders))
                .collect(Collectors.toList());
    }

    private GiftEvent getGift(List<Event> events) {
        return events.stream()
                .filter(e -> e instanceof GiftEvent)
                .map(e -> (GiftEvent) e)
                .findFirst()
                .orElse(null);
    }

    private void printGift(GiftEvent event, Orders orders) {
        Map<Menu, Integer> giftMap = new HashMap<>();
        if (event != null) {
            giftMap = event.getGiftMap(orders.getTotalAmount(), orders.getDate());
        }
        OutputView.printFreeMenu(giftMap);
    }

    private void printBenefitList(List<Event> events, Orders orders) {
        Map<Event, Integer> benefitMap = getBenefitMap(events, orders);
        OutputView.printBenefitList(benefitMap);
    }

    private Map<Event, Integer> getBenefitMap(List<Event> eventList, Orders orders) {
        return eventList.stream()
                .sorted(Comparator.comparingInt((Event e) -> e.getBenefitAmount(orders)).reversed())
                .collect(Collectors.toMap(
                        e -> e,
                        e -> e.getBenefitAmount(orders),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new));
    }

    private void printTotalBenefit(List<Event> events, Orders orders) {
        int totalBenefit = events.stream()
                .mapToInt(e -> e.getBenefitAmount(orders))
                .sum();
        OutputView.printTotalDiscount(totalBenefit);
    }

    private void printTotalAmount(Orders orders) {
        OutputView.printTotalAmount();
    }

    private void printEventBadge(Orders orders) {
        OutputView.printEventBadge();
    }

    private static LocalDate getLocalDate(int date) {
        try {
            return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), date);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ERROR_INVALID_DATE.getInputErrorMessage());
        }
    }

    private static Orders buildOrders(List<String> menuList, LocalDate date) {
        List<OrderMenu> orderMenuList = new ArrayList<>();
        menuList.forEach(m -> orderMenuList.add(new OrderMenu(m)));
        return new Orders(orderMenuList, date);
    }
}
