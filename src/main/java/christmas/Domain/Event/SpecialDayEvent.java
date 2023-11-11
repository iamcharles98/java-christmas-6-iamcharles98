package christmas.Domain.Event;

import christmas.Domain.Reservation;
import java.util.List;

public class SpecialDayEvent extends Event {
    private final int DISCOUNT_UNIT = 1000;
    private final List<Integer> eventDay = List.of(
            3,
            10,
            17,
            24, 25,
            31);

    @Override
    public boolean isEventDay(int day) {
        return eventDay.contains(day);
    }

    @Override
    public void apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice())) {
            int discountMoney = DISCOUNT_UNIT;
        }
    }
}
