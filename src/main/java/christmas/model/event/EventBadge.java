package christmas.model.event;

import java.util.Comparator;
import java.util.List;

public enum EventBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    ;

    private final String name;
    private final int amount;

    EventBadge(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public static EventBadge findByAmount(int amount) {
        return List.of(EventBadge.values()).stream()
                .sorted(Comparator.comparingInt(EventBadge::getAmount).reversed())
                .filter(eb -> eb.amount < amount)
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
