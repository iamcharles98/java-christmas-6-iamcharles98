package christmas;

import christmas.Controller.Controller;

public class Application {
    static Controller controller = Controller.getInstance();
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        controller.init();
        controller.makeReservationProcess();
        controller.showCheckOutProcess();
    }
}
