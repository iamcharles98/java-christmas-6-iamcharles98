package christmas.View;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final String RESERVATION_DATE_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private final String RESERVATION_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물 파스타-2,레드와인-1,초코케이크-1)";

    private static final InputView INSTANCE = new InputView(InputValidator.getInstance());
    private final InputValidator inputValidator;

    private InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public void showGreetingMessage() {
        System.out.println(GREETING_MESSAGE);
    }

    public int receiveReservationDate() {
        System.out.println(RESERVATION_DATE_MESSAGE);
        String input = Console.readLine();
        int reservationDate = inputValidator.validateReservationDate(input);
        return reservationDate;
    }

    public String[] receiveReservationMenu() {
        System.out.println(RESERVATION_MENU_MESSAGE);
        String input = Console.readLine();
        String[] reservationMenus = inputValidator.validateReservationMenu(input);
        return reservationMenus;
    }
}
