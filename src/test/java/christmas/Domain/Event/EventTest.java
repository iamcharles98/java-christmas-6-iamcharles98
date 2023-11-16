package christmas.Domain.Event;

import static org.junit.jupiter.api.Assertions.*;

import christmas.Domain.Benefit;
import christmas.Domain.Menu;
import christmas.Domain.Reservation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EventTest {

    List<Event> eventList;

    @BeforeEach
    void setUp() {
        eventList = List.of(
                new ChristmasEvent(),
                new WeekDayEvent(),
                new WeekendDayEvent(),
                new SpecialDayEvent(),
                new GiftEvent());
    }

    @DisplayName("이벤트는 주문금액 10000원 이상부터 적용 가능하다.")
    @Test
    void askCanMeetBasicEventCondition() {
        //given
        int totalPrice = 9999;

        //when
        List<Boolean> results = new ArrayList<>();
        for (Event e : eventList) {
            results.add(e.canMeetCondition(totalPrice));
        }

        //then
        Assertions.assertThat(results.contains(Boolean.TRUE)).isFalse();
    }

    @DisplayName("크리스마스 디데이 기간에는 크리스마스 디데이 할인 이벤트가 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25})
    void askCanApplyChristMasEvent(int day) {
        //given
        Event event = eventList.get(0);

        //when
        assert event instanceof ChristmasEvent;

        //then
        Assertions.assertThat(event.isEventDay(day)).isTrue();
    }

    @DisplayName("크리스마스 디데이 기간이 아니라면 크리스마스 디데이 할인 이벤트가 적용되지 않는다.")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void askCantApplyChristMasEvent(int day) {
        //given
        Event event = eventList.get(0);

        //when
        assert event instanceof ChristmasEvent;

        //then
        Assertions.assertThat(event.isEventDay(day)).isFalse();
    }

    @DisplayName("평일에는 평일 할인 이벤트가 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void askCanApplyWeekDayEvent(int day) {
        //given
        Event event = eventList.get(1);

        //when
        assert event instanceof WeekDayEvent;

        //then
        Assertions.assertThat(event.isEventDay(day)).isTrue();
    }

    @DisplayName("주말에는 주말 할인 이벤트가 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void askCanApplyWeekendDayEvent(int day) {
        //given
        Event event = eventList.get(2);

        //when
        assert event instanceof WeekendDayEvent;

        //then
        Assertions.assertThat(event.isEventDay(day)).isTrue();
    }

    @DisplayName("별이 표시된 날에는 특별 할인 이벤트가 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void askCanApplySpecialDayEvent(int day) {
        //given
        Event event = eventList.get(3);

        //when
        assert event instanceof SpecialDayEvent;

        //then
        Assertions.assertThat(event.isEventDay(day)).isTrue();
    }

    @DisplayName("12월의 모든날은 증정 할인 이벤트가 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26,
            27, 28, 29, 30, 31})
    void askCanApplyGiftEvent(int day) {
        //given
        Event event = eventList.get(4);

        //when
        assert event instanceof GiftEvent;

        //then
        Assertions.assertThat(event.isEventDay(day)).isTrue();
    }

    @DisplayName("증정 이벤트는 구매금액이 12만원 이상이여야 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 60000, 119998, 119999})
    void askCanMeetGiftEventCondition(int totalPrice) {
        //given
        Event event = eventList.get(4);

        //when
        assert event instanceof GiftEvent;

        //then
        Assertions.assertThat(event.canMeetCondition(totalPrice)).isFalse();
    }

    @DisplayName("평일 이벤트는 디저트 메뉴 1개당 2023원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 11, 12, 13})
    void checkDiscountPriceOfWeekDayEvent(int day) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHOCO_CAKE, 3);
        order.put(Menu.ICE_CREAM, 3);

        //when
        Reservation reservation = new Reservation(order, day);
        Event event = eventList.get(1);
        assert event instanceof WeekDayEvent;
        Optional<Benefit> benefit = event.apply(reservation);

        //then
        Assertions.assertThat(benefit.get().getDiscountMoney()).isEqualTo(2023 * 6);
    }

    @DisplayName("주말 이벤트는 메인 메뉴 1개당 2023원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 8, 15, 22})
    void checkDiscountPriceOfWeekendDayEvent(int day) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 3);
        order.put(Menu.LIB, 3);

        //when
        Reservation reservation = new Reservation(order, day);
        Event event = eventList.get(2);
        assert event instanceof WeekendDayEvent;
        Optional<Benefit> benefit = event.apply(reservation);

        //then
        Assertions.assertThat(benefit.get().getDiscountMoney()).isEqualTo(2023 * 6);
    }

    @DisplayName("특별할인 이벤트는 1000원을 할인한다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24})
    void checkDiscountPriceOfSpecialDayEvent(int day) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 3);
        order.put(Menu.LIB, 3);

        //when
        Reservation reservation = new Reservation(order, day);
        Event event = eventList.get(3);
        assert event instanceof SpecialDayEvent;
        Optional<Benefit> benefit = event.apply(reservation);

        //then
        Assertions.assertThat(benefit.get().getDiscountMoney()).isEqualTo(1000);
    }

    @DisplayName("크리스마스 디데이 이벤트는 1일에 1000원으로 시작하여 25일까지 매일 100원씩 할인금액이 더해진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void checkDiscountPriceOfChristmasDayEvent(int day) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 3);
        order.put(Menu.LIB, 3);

        //when
        Reservation reservation = new Reservation(order, day);
        Event event = eventList.get(0);
        assert event instanceof ChristmasEvent;
        Optional<Benefit> benefit = event.apply(reservation);

        //then
        Assertions.assertThat(benefit.get().getDiscountMoney()).isEqualTo(1000 + 100 * (day - 1));
    }

    @DisplayName("증정 이벤트는 주문금액이 12만원 이상일 때 샴페인 한 병이 주어진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void checkDiscountPriceOfGiftEvent(int day) {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 3);
        order.put(Menu.LIB, 3);

        //when
        Reservation reservation = new Reservation(order, day);
        Event event = eventList.get(4);
        assert event instanceof GiftEvent;
        Optional<Benefit> benefit = event.apply(reservation);

        //then
        Assertions.assertThat(benefit.get().getDiscountMoney()).isEqualTo(Menu.CHAMPAGNE.getPrice());
    }
}