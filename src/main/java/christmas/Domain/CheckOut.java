package christmas.Domain;

import java.util.Arrays;
import java.util.List;

public class CheckOut {
    private final Reservation reservation;
    private final List<Benefit> benefits;
    private Badge myBadge;

    public CheckOut(Reservation reservation, List<Benefit> benefits) {
        this.reservation = reservation;
        this.benefits = benefits;
        choiceBadge();
    }

    public int getAmountOfBenefit() {
        int benefitAmount = 0;
        for (Benefit benefit : benefits) {
            benefitAmount += benefit.getDiscountMoney();
        }
        return benefitAmount;
    }

    public int getExpectPrice() {
        return reservation.getTotalPrice() - getDiscountAmount();
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public Badge getMyBadge() {
        return myBadge;
    }

    public boolean hasGiftBenefit() {
        return benefits.stream().anyMatch(benefit -> benefit.isGiftBenefit());
    }

    private int getDiscountAmount() {
        int discountAmount = 0;
        for (Benefit benefit : benefits) {
            if (!benefit.isGiftBenefit()) {
                discountAmount += benefit.getDiscountMoney();
            }
        }
        return discountAmount;
    }

    private void choiceBadge() {
        int benefitAmount = getAmountOfBenefit();
        myBadge = Arrays.stream(Badge.values()).filter(badge -> badge.apply(benefitAmount)).findFirst().get();
    }
}
