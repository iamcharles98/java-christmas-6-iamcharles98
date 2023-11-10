package christmas.View;

public class InputValidator {
    private final String NUMBER_REGEX = "^[0-9]+$";
    private final String KOREAN_REGEX = "^[ㄱ-ㅎ가-힣]+$";
    private final int FIRST_DATE = 1;
    private final int LAST_DATE = 31;
    private final String ORDER_DELIMITER = ",";
    private final String NAME_AND_AMOUNT_DELIMITER = "-";
    private final int NAME_INDEX = 0;
    private final int AMOUNT_INDEX = 1;
    private final int MIN_AMOUNT = 1;
    private final int MAX_AMOUNT = 20;

    private static final InputValidator INSTANCE = new InputValidator();

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        return INSTANCE;
    }

    public int validateReservationDate(String input) throws IllegalArgumentException {
        if (!isNumber(input)) {
            throw new IllegalArgumentException();
        }
        int date = Integer.parseInt(input);
        if (!isDecemberDate(date)) {
            throw new IllegalArgumentException();
        }
        return date;
    }

    public String[] validateReservationMenu(String input) throws IllegalArgumentException {
        String[] orders = input.split(ORDER_DELIMITER);
        for (String order : orders) {
            validateOrder(order);
        }
        return orders;
    }

    private void validateOrder(String order) throws IllegalArgumentException {
        if (!isOrderFormat(order)) {
            throw new IllegalArgumentException();
        }
        String[] orderInfo = order.split(NAME_AND_AMOUNT_DELIMITER);
        validateOrderInfo(orderInfo);
    }

    private void validateOrderInfo(String[] orderInfo) {
        String orderName = orderInfo[NAME_INDEX];
        String orderAmount = orderInfo[AMOUNT_INDEX];
        if (!isKorean(orderName)) {
            throw new IllegalArgumentException();
        }
        if (!isNumber(orderAmount)) {
            throw new IllegalArgumentException();
        }
        int amount = Integer.parseInt(orderAmount);
        if (!isInBoundary(amount)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInBoundary(int amount) {
        return amount >= MIN_AMOUNT && amount <= MAX_AMOUNT;
    }

    private boolean isKorean(String input) {
        return input.matches(KOREAN_REGEX);
    }

    private boolean isOrderFormat(String order) {
        return order.contains(NAME_AND_AMOUNT_DELIMITER);
    }

    private boolean isNumber(String input) {
        return input.matches(NUMBER_REGEX);
    }

    private boolean isDecemberDate(int date) {
        return FIRST_DATE <= date && LAST_DATE >= date;
    }
}
