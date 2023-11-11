package christmas.Domain.Event;

import christmas.Domain.Reservation;

public abstract class Event {
    private final int EVENT_CONDITION = 10000;
    public boolean canMeetCondition(int totalPrice) {
        return totalPrice >= EVENT_CONDITION;
    }
    public abstract boolean isEventDay(int day);

    public abstract void apply(Reservation reservation);
}
