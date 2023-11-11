package christmas.Domain.Event;

import christmas.Domain.Benefit;
import christmas.Domain.Reservation;
import java.util.List;
import java.util.Optional;

public class WeekDayEvent extends Event {
    private final int DISCOUNT_UNIT = 2023;
    private final List<Integer> eventDay = List.of(
            4, 5, 6, 7,
            11, 12, 13, 14,
            18, 19, 20, 21,
            25, 26, 27, 28);

    public WeekDayEvent() {
    }

    @Override
    public boolean isEventDay(int day) {
        return eventDay.contains(day);
    }

    @Override
    public Optional<Benefit> apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice())) {
            int countOfDessertMenu = reservation.NumberOfDessertMenu();
            int discountMoney = countOfDessertMenu * DISCOUNT_UNIT;
            return Optional.of(new Benefit(this, discountMoney));
        }
        return Optional.empty();
    }
}
