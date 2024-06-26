package christmas.Domain.Event;

import christmas.Domain.Benefit;
import christmas.Domain.Reservation;
import java.util.List;
import java.util.Optional;

public class ChristmasEvent extends Event {
    private final String eventName = "크리스마스 디데이 할인";
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
    public Optional<Benefit> apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice()) && isEventDay(reservation.getDay())) {
            int discountMoney = getDiscountMoney(reservation.getDay());
            return Optional.of(new Benefit(this, discountMoney));
        }
        return Optional.empty();
    }

    @Override
    public String name() {
        return eventName;
    }

    private int getDiscountMoney(int reservationDay) {
        return DISCOUNT_BASE_UNIT + (DISCOUNT_ADD_UNIT * (reservationDay - ADJUST_NUMBER));
    }
}
