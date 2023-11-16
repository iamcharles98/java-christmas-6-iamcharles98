package christmas.View;

import static christmas.Utils.Constants.LINE;
import static christmas.Utils.Constants.MINUS;
import static christmas.Utils.Constants.SPACE;
import static christmas.Utils.Constants.ZERO;

import christmas.Domain.Badge;
import christmas.Domain.Benefit;
import christmas.Domain.CheckOut;
import christmas.Domain.Menu;
import christmas.Domain.Reservation;
import java.util.List;

public class OutputView {
    private final String EVENT_PREVIEW_MESSAGE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리보기!";
    private final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private final String ORDER_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private final String PRICE_FORMAT = "%,d원";
    private final String GIFT_MESSAGE = "<증정 메뉴>";
    private final String GIFT = "샴페인 1개";
    private final String BENEFIT_MESSAGE = "<혜택 내역>";
    private final String TOTAL_DISCOUNT_MESSAGE = "<총혜택 금액>";
    private final String DISCOUNT_ORDER_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    private final String NOTHING = "없음";
    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void showPreviewMessage(int date) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(EVENT_PREVIEW_MESSAGE_FORMAT, date)).append(LINE);
        System.out.println(stringBuilder);
    }

    public void showEventResult(CheckOut checkOut) {
        showOrderMenu(checkOut.getReservation());
        showOrderPrice(checkOut.getReservation().getTotalPrice());
        showGift(checkOut.hasGiftBenefit());
        showBenefits(checkOut.getBenefits());
        showTotalDiscountMoney(checkOut.getAmountOfBenefit());
        showExpectPrice(checkOut.getExpectPrice());
        showBadge(checkOut.getMyBadge());
    }

    private void showGift(boolean hasGiftBenefit) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GIFT_MESSAGE).append(LINE);
        if (hasGiftBenefit) {
            stringBuilder.append(GIFT).append(LINE);
            System.out.println(stringBuilder.toString());
            return;
        }
        stringBuilder.append(NOTHING).append(LINE);
        System.out.println(stringBuilder.toString());
    }

    private void showOrderMenu(Reservation reservation) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ORDER_MENU_MESSAGE)
                .append(LINE)
                .append(reservation.toString());
        System.out.println(stringBuilder.toString());
    }

    private void showOrderPrice(int price) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ORDER_PRICE_MESSAGE)
                .append(LINE)
                .append(String.format(PRICE_FORMAT, price))
                .append(LINE);
        System.out.println(stringBuilder.toString());
    }

    private void showBenefits(List<Benefit> benefits) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BENEFIT_MESSAGE).append(LINE);
        if (benefits.isEmpty()) {
            stringBuilder.append(NOTHING).append(LINE);
            System.out.println(stringBuilder.toString());
            return;
        }
        for (Benefit benefit : benefits) {
            stringBuilder.append(benefit.toString());
        }
        System.out.println(stringBuilder.toString());
    }

    private void showTotalDiscountMoney(int discountMoney) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TOTAL_DISCOUNT_MESSAGE).append(LINE);
        if (discountMoney != ZERO) {
            stringBuilder.append(MINUS);
        }
        stringBuilder.append(String.format(PRICE_FORMAT, discountMoney)).append(LINE);
        System.out.println(stringBuilder.toString());
    }

    private void showExpectPrice(int expectPrice) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DISCOUNT_ORDER_PRICE_MESSAGE).append(LINE)
                .append(String.format(PRICE_FORMAT, expectPrice)).append(LINE);
        System.out.println(stringBuilder.toString());
    }

    private void showBadge(Badge badge) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(EVENT_BADGE_MESSAGE).append(LINE)
                .append(badge.toString());
        System.out.println(stringBuilder.toString());
    }
}
