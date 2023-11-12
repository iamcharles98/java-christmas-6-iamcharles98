package christmas.Domain;

import static christmas.Utils.Constants.COLON;
import static christmas.Utils.Constants.LINE;
import static christmas.Utils.Constants.MINUS;
import static christmas.Utils.Constants.SPACE;
import static christmas.Utils.Constants.ZERO;

import christmas.Domain.Event.Event;
import christmas.Domain.Event.GiftEvent;

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
        stringBuilder.append(event.name()).append(COLON).append(SPACE);
        if (discountMoney != ZERO) {
            stringBuilder.append(MINUS);
        }
        stringBuilder
                .append(String.format("%,d원", discountMoney))
                .append(LINE);
        return stringBuilder.toString();
    }
}
