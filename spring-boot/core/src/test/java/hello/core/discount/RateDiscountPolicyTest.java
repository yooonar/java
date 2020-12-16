package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    // 정률 할인 10%
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        // given
        // VIP 회원 생성
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = discountPolicy.discount(member, 10000);

        // then
        // Assertions.assertThat(discount).isEqualTo(1000);
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("BASIC 은 할인이 적용되지 않아야 한다.")
    void vip_x() {
        // BASIC 회원 생성
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);

        // Assertions.assertThat(discount).isEqualTo(0);
        assertThat(discount).isEqualTo(0);
    }
}