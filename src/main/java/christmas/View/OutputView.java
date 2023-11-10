package christmas.View;

public class OutputView {
    private final String EVENT_PREVIEW_MESSAGE_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리보기!";
    private final String ORDER_MENU_MESSAGE = "<주문 메뉴>";
    private final String ORDER_PRICE_MESSAGE = "<할인 전 총주문 금액>";
    private final String GIFT_MESSAGE = "<증정 메뉴>";
    private final String DISCOUNT_MESSAGE = "<혜택 내역>";
    private final String TOTAL_DISCOUNT_MESSAGE = "<총혜택 금액>";
    private final String DISCOUNT_ORDER_PRICE_MESSAGE = "<할인 후 예상 결제 금액>";
    private final String EVENT_BADGE_MESSAGE = "<12월 이벤트 배지>";
    private static final OutputView INSTANCE = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }
}
