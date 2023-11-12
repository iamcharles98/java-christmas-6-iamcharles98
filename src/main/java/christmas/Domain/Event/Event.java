package christmas.Domain.Event;

import christmas.Domain.Benefit;
import christmas.Domain.Reservation;
import java.util.Optional;

public abstract class Event {
    private final int EVENT_CONDITION = 10000;

    public boolean canMeetCondition(int totalPrice) {
        return totalPrice >= EVENT_CONDITION;
    }

    public abstract boolean isEventDay(int day);

    public abstract Optional<Benefit> apply(Reservation reservation);

    public abstract String name();
}
