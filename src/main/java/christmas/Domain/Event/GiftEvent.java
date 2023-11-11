package christmas.Domain.Event;

import christmas.Domain.Menu;
import christmas.Domain.Reservation;
import java.util.List;

public class GiftEvent extends Event {
    private final Menu GIFT = Menu.CHAMPAGNE;
    private final int GIFT_CONDITION = 120000;
    private final List<Integer> eventDay = List.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25,
            26, 27, 28, 29, 30, 31);

    @Override
    public boolean isEventDay(int day) {
        return eventDay.contains(day);
    }

    @Override
    public void apply(Reservation reservation) {
        if (super.canMeetCondition(reservation.getTotalPrice()) && canMeetGiftCondition(reservation.getTotalPrice())) {
            int discountPrice = GIFT.getPrice();
        }
    }

    private boolean canMeetGiftCondition(int totalPrice) {
        return totalPrice >= GIFT_CONDITION;
    }
}
