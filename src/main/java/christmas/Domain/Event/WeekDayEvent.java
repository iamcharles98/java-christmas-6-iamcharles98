package christmas.Domain.Event;

import christmas.Domain.Benefit;
import christmas.Domain.Reservation;
import java.util.List;
import java.util.Optional;

public class WeekDayEvent extends Event {
    private final String eventName = "평일 할인";
    private final int DISCOUNT_UNIT = 2023;
    private final List<Integer> eventDay = List.of(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28, 31);

    public WeekDayEvent() {
    }

    @Override
    public boolean isEventDay(int day) {
        return eventDay.contains(day);
    }

    @Override
    public Optional<Benefit> apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice()) && isEventDay(reservation.getDay())) {
            int countOfDessertMenu = reservation.NumberOfDessertMenu();
            int discountMoney = countOfDessertMenu * DISCOUNT_UNIT;
            return Optional.of(new Benefit(this, discountMoney));
        }
        return Optional.empty();
    }

    @Override
    public String name() {
        return eventName;
    }
}
