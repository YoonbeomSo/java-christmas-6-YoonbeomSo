package christmas.service;

import christmas.model.event.Event;
import christmas.model.event.EventBadge;
import christmas.model.event.EventDate;
import christmas.model.order.OrderMenu;
import christmas.model.order.Orders;
import christmas.model.event.ActiveEvent;
import christmas.model.event.detail.GiftEvent;
import christmas.model.order.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class ChristmasService {

    public void eventStart() {
        Orders orders = reservation();

        printBenefitForeword(orders.getDate());
        printMenus(orders.getOrderList());
        printOriginalAmount(orders.getTotalAmount());

        List<Event> events = getValidEventList(orders);

        printGift(events, orders);
        printBenefitList(events, orders);
        printTotalBenefit(events, orders);
        printResultAmount(orders);
        printEventBadge(orders);
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
            return EventDate.findLocalDateByDay(InputView.readDate());
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

    private void printBenefitForeword(LocalDate date) {
        OutputView.printBenefitForeword(date);
    }

    private void printMenus(List<String> orderList) {
        OutputView.printMenu(orderList);
    }

    private void printOriginalAmount(int totalAmount) {
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

    private void printGift(List<Event> events, Orders orders) {
        GiftEvent giftEvent = getGift(events);
        Map<Menu, Integer> giftMap = new HashMap<>();
        if (giftEvent != null) {
            giftMap = giftEvent.getGiftMap(orders.getTotalAmount(), orders.getDate());
            orders.setGiftBenefitAmount(giftEvent.getBenefitAmount(orders));
        }
        OutputView.printFreeMenu(giftMap);
    }

    private void printBenefitList(List<Event> events, Orders orders) {
        Map<Event, Integer> benefitMap = getBenefitMap(events, orders);
        OutputView.printBenefitList(benefitMap);
    }

    private Map<Event, Integer> getBenefitMap(List<Event> events, Orders orders) {
        return events.stream()
                .sorted(Comparator.comparingInt((Event e) -> e.getBenefitAmount(orders)).reversed())
                .filter(e -> e.getBenefitAmount(orders) != 0)
                .collect(Collectors.toMap(
                        e -> e,
                        e -> (e.getBenefitAmount(orders) * -1),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new));
    }

    private void printTotalBenefit(List<Event> events, Orders orders) {
        orders.setBenefitAmount(events);
        OutputView.printTotalBenefit(orders.getBenefitAmount());
    }

    private void printResultAmount(Orders orders) {
        int resultAmount = orders.getResultAmount();
        OutputView.printResultAmount(resultAmount);
    }

    private void printEventBadge(Orders orders) {
        EventBadge eventBadge = orders.getBadge();
        OutputView.printEventBadge(eventBadge);
    }

    private static Orders buildOrders(List<String> menuList, LocalDate date) {
        List<OrderMenu> orderMenuList = new ArrayList<>();
        menuList.forEach(m -> orderMenuList.add(new OrderMenu(m)));
        return new Orders(orderMenuList, date);
    }
}
