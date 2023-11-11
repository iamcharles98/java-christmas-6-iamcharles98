package christmas.Domain;

public abstract class Event {
    public abstract boolean isEventDay(int day);

    public abstract void apply(Reservation reservation);
}
