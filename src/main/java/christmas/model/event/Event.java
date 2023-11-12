package christmas.model.event;

import christmas.model.order.Orders;

public abstract class Event {
    public abstract boolean isValidEvent(Orders orders);

    public abstract Integer getBenefitAmount(Orders orders);

    public abstract String getName();
}
