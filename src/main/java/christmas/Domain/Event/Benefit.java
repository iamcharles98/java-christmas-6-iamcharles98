package christmas.Domain.Event;

import static christmas.Constants.COLON;
import static christmas.Constants.MINUS;
import static christmas.Constants.SPACE;

public class Benefit {
    private final Event event;
    private final int discountMoney;

    public Benefit(Event event, int discountMoney) {
        this.discountMoney = discountMoney;
        this.event = event;
    }

    public int getDiscountMoney() {
        return discountMoney;
    }

    public boolean isGiftBenefit() {
        return event instanceof GiftEvent;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(event.toString()).append(COLON).append(SPACE).append(MINUS)
                .append(String.format("%,d원", discountMoney));
        return stringBuilder.toString();
    }
}
