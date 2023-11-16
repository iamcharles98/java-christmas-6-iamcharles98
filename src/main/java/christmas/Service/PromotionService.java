package christmas.Service;

import christmas.Domain.Benefit;
import christmas.Domain.CheckOut;
import christmas.Domain.Menu;
import christmas.Domain.Reservation;
import java.util.List;
import java.util.Map;

public class PromotionService {
    private Reservation reservation;
    private final EventService eventService;
    private static final PromotionService INSTANCE = new PromotionService(EventService.getInstance());

    private PromotionService(EventService eventService) {
        this.eventService = eventService;
    }

    public static PromotionService getInstance() {
        return INSTANCE;
    }

    public void createReservation(Map<Menu, Integer> menus, int day) {
        reservation = new Reservation(menus, day);
    }

    public CheckOut createCheckOut() {
        List<Benefit> benefits = eventService.getBenefitsOfApplicableEvent(reservation);
        return new CheckOut(reservation, benefits);
    }
}
