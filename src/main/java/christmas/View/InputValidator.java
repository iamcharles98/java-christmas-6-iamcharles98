package christmas.View;

import static christmas.Utils.Constants.AMOUNT_INDEX;
import static christmas.Utils.Constants.FIRST_DATE;
import static christmas.Utils.Constants.LAST_DATE;
import static christmas.Utils.Constants.MAX_AMOUNT;
import static christmas.Utils.Constants.MIN_AMOUNT;
import static christmas.Utils.Constants.NAME_AND_AMOUNT_DELIMITER;
import static christmas.Utils.Constants.NAME_INDEX;
import static christmas.Utils.Constants.ORDER_DELIMITER;

import christmas.Utils.ErrorMessage;
import java.util.Arrays;

public class InputValidator {
    private final String NUMBER_REGEX = "^[0-9]+$";
    private final String KOREAN_REGEX = "^[ㄱ-ㅎ가-힣]+$";
    private final String PARTIALLY_NUMBER = "[0-9]";

    private static final InputValidator INSTANCE = new InputValidator();

    private InputValidator() {
    }

    public static InputValidator getInstance() {
        return INSTANCE;
    }

    public int validateReservationDate(String input) throws IllegalArgumentException {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
        int date = Integer.parseInt(input);
        if (!isDecemberDate(date)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
        return date;
    }

    public String[] validateReservationMenu(String input) throws IllegalArgumentException {
        String[] orders = input.split(ORDER_DELIMITER);
        if (hasDupOrder(orders)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        for (String order : orders) {
            validateOrder(order);
        }
        return orders;
    }

    private boolean hasDupOrder(String[] orders) {
        return Arrays.stream(orders)
                .map(s -> s.replaceAll(PARTIALLY_NUMBER, "")).distinct().count() != orders.length;
    }

    private void validateOrder(String order) throws IllegalArgumentException {
        if (!isOrderFormat(order)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        String[] orderInfo = order.split(NAME_AND_AMOUNT_DELIMITER);
        validateOrderInfo(orderInfo);
    }

    private void validateOrderInfo(String[] orderInfo) {
        String orderName = orderInfo[NAME_INDEX];
        String orderAmount = orderInfo[AMOUNT_INDEX];
        if (!isKorean(orderName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (!isNumber(orderAmount)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        int amount = Integer.parseInt(orderAmount);
        if (!isInBoundary(amount)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
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
