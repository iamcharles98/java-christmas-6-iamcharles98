package christmas.Service;

import static org.junit.jupiter.api.Assertions.*;

import christmas.Domain.Benefit;
import christmas.Domain.Event.ChristmasEvent;
import christmas.Domain.Menu;
import christmas.Domain.Reservation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventServiceTest {

    EventService eventService;

    private Reservation makeReservation(Map<Menu, Integer> order, int date) {
        return new Reservation(order, date);
    }

    @BeforeEach
    void setUp() {
        eventService = EventService.getInstance();
    }

    @DisplayName("크리스마스 기간에 예약 시 크리스마스 디데이 이벤트가 적용된 혜택을 가진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 15, 24, 25})
    void hasBenefitOfChristmasEventDuringChristmas(int date) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHAMPAGNE, 1);

        //when
        Reservation reservation = makeReservation(order, date);
        List<Benefit> benefits = eventService.getBenefitsOfApplicableEvent(reservation);

        //then
        Assertions.assertThat(benefits.stream()
                        .filter(benefit -> benefit.createdBy().equals("크리스마스 디데이 할인")).count())
                .isEqualTo(1);
    }

    @DisplayName("별 기간에 예약 시 특별할인 이벤트가 적용된 혜택을 가진다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void hasBenefitOfSpecialEventDuringStarDay(int date) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHAMPAGNE, 1);

        //when
        Reservation reservation = makeReservation(order, date);
        List<Benefit> benefits = eventService.getBenefitsOfApplicableEvent(reservation);

        //then
        Assertions.assertThat(benefits.stream()
                        .filter(benefit -> benefit.createdBy().equals("특별 할인")).count())
                .isEqualTo(1);
    }

    @DisplayName("평일 기간에 예약 시 평일할인 이벤트가 적용된 혜택을 가진다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 11, 19, 27, 28})
    void hasBenefitOfWeekDayEventDuringWeekDay(int date) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHAMPAGNE, 1);

        //when
        Reservation reservation = makeReservation(order, date);
        List<Benefit> benefits = eventService.getBenefitsOfApplicableEvent(reservation);

        //then
        Assertions.assertThat(benefits.stream()
                        .filter(benefit -> benefit.createdBy().equals("평일 할인")).count())
                .isEqualTo(1);
    }

    @DisplayName("주말 기간에 예약 시 주말할인 이벤트가 적용된 혜택을 가진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void hasBenefitOfWeekEndDayEventDuringWeekEndDay(int date) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHAMPAGNE, 1);

        //when
        Reservation reservation = makeReservation(order, date);
        List<Benefit> benefits = eventService.getBenefitsOfApplicableEvent(reservation);

        //then
        Assertions.assertThat(benefits.stream()
                        .filter(benefit -> benefit.createdBy().equals("주말 할인")).count())
                .isEqualTo(1);
    }

    @DisplayName("12만원 이상 주문예약 시 증정 이벤트가 적용된 혜택을 가진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 15, 20, 25, 30})
    void hasBenefitOfGiftEvent(int date) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 2);
        order.put(Menu.CHAMPAGNE, 1);

        //when
        Reservation reservation = makeReservation(order, date);
        List<Benefit> benefits = eventService.getBenefitsOfApplicableEvent(reservation);
        assert reservation.getTotalPrice() >= 120000;
        //then
        Assertions.assertThat(benefits.stream()
                        .filter(benefit -> benefit.createdBy().equals("증정 이벤트")).count())
                .isEqualTo(1);
    }

}