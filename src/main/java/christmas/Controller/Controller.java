package christmas.Controller;

import christmas.Domain.CheckOut;
import christmas.Domain.Menu;
import christmas.Domain.Reservation;
import christmas.Utils.InputResolver;
import christmas.Service.PromotionService;
import christmas.View.InputView;
import christmas.View.OutputView;
import java.util.Map;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final PromotionService promotionService;

    private static final Controller INSTANCE = new Controller(InputView.getInstance(), OutputView.getInstance(),
            PromotionService.getInstance());

    private Controller(InputView inputView, OutputView outputView, PromotionService promotionService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.promotionService = promotionService;
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void init() {
        inputView.showGreetingMessage();
    }

    public void makeReservationProcess() {
        int visitDate = receiveVisitDate();
        Reservation reservation = makeReservation(visitDate);
        outputView.showPreviewMessage(visitDate);
    }

    public void showCheckOutProcess() {
        CheckOut checkOut = promotionService.createCheckOut();
        outputView.showEventResult(checkOut);
    }

    private int receiveVisitDate() {
        int date = 0;
        try {
            date = inputView.receiveReservationDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            date = receiveVisitDate();
        }
        return date;
    }

    private Reservation makeReservation(int date) {
        try {
            String[] reservationMenu = inputView.receiveReservationMenu();
            Map<Menu, Integer> orders = InputResolver.stringToOrderMap(reservationMenu);
            return promotionService.createReservation(orders, date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeReservation(date);
        }
    }
}
