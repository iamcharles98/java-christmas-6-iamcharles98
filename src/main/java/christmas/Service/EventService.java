package christmas.Service;

import christmas.Domain.Benefit;
import christmas.Domain.Event.ChristmasEvent;
import christmas.Domain.Event.Event;
import christmas.Domain.Event.GiftEvent;
import christmas.Domain.Event.SpecialDayEvent;
import christmas.Domain.Event.WeekDayEvent;
import christmas.Domain.Event.WeekendDayEvent;
import christmas.Domain.Reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventService {
    private final List<Event> events = List.of(
            new ChristmasEvent(),
            new WeekDayEvent(),
            new WeekendDayEvent(),
            new SpecialDayEvent(),
            new GiftEvent());
    private static final EventService INSTANCE = new EventService();

    private EventService() {
    }

    public static EventService getInstance() {
        return INSTANCE;
    }

    public List<Benefit> getBenefitsOfApplicableEvent(Reservation reservation) {
        final List<Benefit> benefits = new ArrayList<>();
        for (Event event : events) {
            addBenefitIfApplicable(reservation, benefits, event);
        }
        return benefits;
    }

    private void addBenefitIfApplicable(Reservation reservation, List<Benefit> benefits, Event event) {
        if (event.isEventDay(reservation.getDay())) {
            Optional<Benefit> optionalBenefit = event.apply(reservation);
            if (optionalBenefit.isPresent()) {
                benefits.add(optionalBenefit.get());
            }
        }
    }

}
