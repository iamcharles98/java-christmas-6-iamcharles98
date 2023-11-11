package christmas.Domain;

import java.util.List;

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
    public void apply(Reservation reservation) {
        int countOfDessertMenu = reservation.NumberOfDessertMenu();
        int discountMoney = countOfDessertMenu * DISCOUNT_UNIT;
    }
}
