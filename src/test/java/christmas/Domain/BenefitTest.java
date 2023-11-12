package christmas.Domain;

import static org.junit.jupiter.api.Assertions.*;

import christmas.Domain.Event.ChristmasEvent;
import christmas.Domain.Event.Event;
import christmas.Domain.Event.GiftEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {

    @DisplayName("이벤트 혜택 금액을 반환한다.")
    @Test
    void getDiscountMoney() {
        //given
        int discountPrice = 10000;
        Event event = new ChristmasEvent();

        //when
        Benefit benefit = new Benefit(event, discountPrice);

        //then
        Assertions.assertThat(benefit.getDiscountMoney()).isEqualTo(discountPrice);
    }

    @DisplayName("혜택이 증정 이벤트로 인한 혜택이라면 True 를 반환한다.")
    @Test
    void isGiftBenefit() {
        //given
        int discountPrice = 10000;
        Event event = new GiftEvent();

        //when
        Benefit benefit = new Benefit(event,10000);

        //then
        Assertions.assertThat(benefit.isGiftBenefit()).isTrue();
    }
}