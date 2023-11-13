package christmas.Domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.Domain.Event.ChristmasEvent;
import christmas.Domain.Event.Event;
import christmas.Domain.Event.GiftEvent;
import christmas.Domain.Event.SpecialDayEvent;
import christmas.Domain.Event.WeekDayEvent;
import christmas.Domain.Event.WeekendDayEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CheckOutTest {

    private List<Event> events = List.of(new ChristmasEvent(),
            new WeekDayEvent(),
            new WeekendDayEvent(),
            new SpecialDayEvent(),
            new GiftEvent());

    private List<Benefit> makeBenefits(Reservation reservation) {
        final List<Benefit> benefits = new ArrayList<>();
        for (Event event : events) {
            Optional<Benefit> optionalBenefit = event.apply(reservation);
            if (optionalBenefit.isPresent()) {
                benefits.add(optionalBenefit.get());
            }
        }
        return benefits;
    }

    @DisplayName("총 혜택 금액을 반환한다.")
    @Test
    void getAmountOfBenefit() {
        //given
        int date = 25;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ZERO_COLA, 2);
        Reservation reservation = new Reservation(order,date);

        //when
        List<Benefit> benefits = makeBenefits(reservation);
        CheckOut checkOut = new CheckOut(reservation,benefits);
        int discount = 0;
        for(Benefit b : benefits) {
            discount += b.getDiscountMoney();
        }

        //then
        Assertions.assertThat(checkOut.getAmountOfBenefit()).isEqualTo(discount);
    }

    @DisplayName("증정 이벤트가 적용되지 않은 경우, 예상 결제가격은 총주문 가격에서 할인가격을 뺀 가격이다.")
    @Test
    void getExpectPrice() {
        //given
        int date = 25;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ZERO_COLA, 2);
        Reservation reservation = new Reservation(order,date);

        //when
        List<Benefit> benefits = makeBenefits(reservation);
        CheckOut checkOut = new CheckOut(reservation,benefits);
        int discount = 0;
        for(Benefit b : benefits) {
            discount += b.getDiscountMoney();
        }

        //then
        Assertions.assertThat(checkOut.getExpectPrice()).isEqualTo(reservation.getTotalPrice() - discount);
    }

    @DisplayName("예약에 적용된 혜택들을 반환한다.")
    @Test
    void getBenefits() {
        //given
        int date = 25;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ZERO_COLA, 2);
        Reservation reservation = new Reservation(order,date);

        //when
        List<Benefit> benefits = makeBenefits(reservation);
        CheckOut checkOut = new CheckOut(reservation,benefits);

        //then
        Assertions.assertThat(checkOut.getBenefits()).isEqualTo(benefits);
    }

    @DisplayName("혜택 금액에 따라 부여되는 뱃지를 반환한다.(혜택금액 : 5000원 이상 10000원 미만")
    @Test
    void getMyBadge() {
        //given
        int date = 25;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ZERO_COLA, 2);
        Reservation reservation = new Reservation(order,date);

        //when
        List<Benefit> benefits = makeBenefits(reservation);
        CheckOut checkOut = new CheckOut(reservation,benefits);
        int discount = checkOut.getAmountOfBenefit();
        assert 5000 <= discount && discount < 10000;

        //then
        Assertions.assertThat(checkOut.getMyBadge()).isEqualTo(Badge.STAR);
    }

    @DisplayName("증정 이벤트의 적용 여부를 확인한다. (12만원 이상 주문)")
    @Test
    void hasGiftBenefitIsTrue() {
        //given
        int date = 25;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 2);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ZERO_COLA, 2);
        Reservation reservation = new Reservation(order,date);

        //when
        List<Benefit> benefits = makeBenefits(reservation);
        CheckOut checkOut = new CheckOut(reservation,benefits);

        //then
        Assertions.assertThat(checkOut.hasGiftBenefit()).isTrue();
    }
    @DisplayName("증정 이벤트의 적용 여부를 확인한다. (12만원 이하 주문)")
    @Test
    void hasGiftBenefitIsFalse() {
        //given
        int date = 25;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.LIB, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ZERO_COLA, 2);
        Reservation reservation = new Reservation(order,date);

        //when
        List<Benefit> benefits = makeBenefits(reservation);
        CheckOut checkOut = new CheckOut(reservation,benefits);

        //then
        Assertions.assertThat(checkOut.hasGiftBenefit()).isFalse();
    }
}