package christmas.Domain.Event;

import christmas.Domain.Reservation;
import java.util.List;

public class ChristmasEvent extends Event {
    private final int DISCOUNT_BASE_UNIT = 1000;
    private final int DISCOUNT_ADD_UNIT = 100;
    private final int ADJUST_NUMBER = 1;
    private final List<Integer> eventDay = List.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25);

    @Override
    public boolean isEventDay(int day) {
        return eventDay.contains(day);
    }

    @Override
    public void apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice())) {
            int discountMoney = getDiscountMoney(reservation.getDay());
        }
    }

    private int getDiscountMoney(int reservationDay) {
        return DISCOUNT_BASE_UNIT + (DISCOUNT_ADD_UNIT * (reservationDay - ADJUST_NUMBER));
    }
}
