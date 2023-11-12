package christmas.Domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.Utils.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationTest {

    @DisplayName("음료만 주문한 경우 예외가 발생한다.")
    @Test
    void createReservationByOnlyDrink() {
        //given
        int date = 1;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHAMPAGNE, 1);
        order.put(Menu.ZERO_COLA, 2);

        //when

        //then
        Assertions.assertThatThrownBy(() -> new Reservation(order, date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ONLY_DRINK_ORDER.getMessage());
    }

    @DisplayName("20개가 넘는 주문에 대해서 예외가 발생한다.")
    @Test
    void createReservationByOverTwentyOrder() {
        //given
        int date = 1;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 21);

        //when

        //then
        Assertions.assertThatThrownBy(() -> new Reservation(order, date))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVER_ORDER.getMessage());
    }

    @DisplayName("메인 메뉴의 갯수를 반환한다.")
    @Test
    void returnNumberOfMainMenu() {
        //given
        int date = 1;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHAMPAGNE, 5);
        order.put(Menu.T_BONE_STEAK, 5);
        order.put(Menu.LIB, 5);

        //when
        Reservation reservation = new Reservation(order, date);

        //then
        Assertions.assertThat(reservation.NumberOfMainMenu()).isEqualTo(10);
    }

    @DisplayName("디저트 메뉴의 갯수를 반환한다.")
    @Test
    void returnNumberOfDessertMenu() {
        //given
        int date = 1;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.CHAMPAGNE, 5);
        order.put(Menu.CHOCO_CAKE, 5);
        order.put(Menu.ICE_CREAM, 5);

        //when
        Reservation reservation = new Reservation(order, date);

        //then
        Assertions.assertThat(reservation.NumberOfDessertMenu()).isEqualTo(10);
    }

    @DisplayName("주문 메뉴의 총 가격을 반환한다.")
    @Test
    void returnTotalPrice() {
        //given
        int date = 1;
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.T_BONE_STEAK, 1);
        order.put(Menu.CHOCO_CAKE, 1);
        order.put(Menu.ICE_CREAM, 1);

        //when
        Reservation reservation = new Reservation(order, date);

        //then
        Assertions.assertThat(reservation.getTotalPrice())
                .isEqualTo(Menu.T_BONE_STEAK.getPrice() + Menu.CHOCO_CAKE.getPrice() + Menu.ICE_CREAM.getPrice());
    }


}