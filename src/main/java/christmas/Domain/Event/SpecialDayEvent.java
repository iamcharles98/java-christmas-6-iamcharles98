package christmas.Domain.Event;

import christmas.Domain.Benefit;
import christmas.Domain.Reservation;
import java.util.List;
import java.util.Optional;

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
    public Optional<Benefit> apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice())) {
            return Optional.of(new Benefit(this, DISCOUNT_UNIT));
        }
        return Optional.empty();
    }
}
