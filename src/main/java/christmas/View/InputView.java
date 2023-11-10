package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String RESERVATION_DATE_MESSAGE = " 12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final String RESERVATION_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물 파스타-2,레드와인-1,초코케이크-1)";

    private static final InputView INSTANCE = new InputView();

    private InputView() {
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public String receiveReservationDate() {
        System.out.println(RESERVATION_DATE_MESSAGE);
        String input = Console.readLine();
        /**
         * 입력 날짜의 입력 유효성 검증 후 int 로 변경하여 반환
         */
        return input;
    }

    public String receiveReservationMenu() {
        System.out.println(RESERVATION_MENU_MESSAGE);
        String input = Console.readLine();
        /**
         * 입력 메뉴의 입력 유효성 검증 후 메뉴, 메뉴 개수 단위로 변경하여 반환
         */
        return input;
    }
}
