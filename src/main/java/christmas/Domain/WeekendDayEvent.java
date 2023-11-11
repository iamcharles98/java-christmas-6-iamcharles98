package christmas.Domain;

import java.util.List;

public class WeekendDayEvent extends Event {
    private final int DISCOUNT_UNIT = 2023;
    private final List<Integer> eventDay = List.of(
            1, 2,
            8, 9,
            15, 16,
            22, 23,
            29, 30);

    public WeekendDayEvent() {
    }

    @Override
    public boolean isEventDay(int day) {
        return eventDay.contains(day);
    }

    @Override
    public void apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice())) {
            int countOfMainMenu = reservation.NumberOfMainMenu();
            int discountMoney = reservation.NumberOfMainMenu() * DISCOUNT_UNIT;
        }
    }
}
