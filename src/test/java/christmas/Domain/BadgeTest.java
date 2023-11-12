package christmas.Domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BadgeTest {
    @DisplayName("NOTHING 뱃지는 혜택금액이 5000원 미만인 경우 해당된다.")
    @ParameterizedTest
    @ValueSource(ints = {0,1,2500,4999})
    void noBadge(int benefitAmount) {
        Badge badge = Badge.NOTHING;

        Assertions.assertThat(badge.apply(benefitAmount)).isTrue();
    }
    @DisplayName("STAR 뱃지는 혜택금액이 5000원 이상 10000원 미만인 경우 해당된다.")
    @ParameterizedTest
    @ValueSource(ints = {5000,5001,7500,9999})
    void starBadge(int benefitAmount) {
        Badge badge = Badge.STAR;

        Assertions.assertThat(badge.apply(benefitAmount)).isTrue();
    }
    @DisplayName("TREE 뱃지는 혜택금액이 10000원 이상 20000원 미만인 경우 해당된다.")
    @ParameterizedTest
    @ValueSource(ints = {10000,10001,15000,19999})
    void treeBadge(int benefitAmount) {
        Badge badge = Badge.TREE;

        Assertions.assertThat(badge.apply(benefitAmount)).isTrue();
    }
    @DisplayName("SANTA 뱃지는 혜택금액이 20000원 이상인 경우 해당된다.")
    @ParameterizedTest
    @ValueSource(ints = {20000,20001,50000,100000,100001})
    void santaBadge(int benefitAmount) {
        Badge badge = Badge.SANTA;

        Assertions.assertThat(badge.apply(benefitAmount)).isTrue();
    }


}