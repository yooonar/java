package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정율 할인

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void vip_o() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);// VIP 회원

        // when
        int discount = discountPolicy.discount(member, 10000); // 할인 금액 확인

        // then
        assertThat(discount).isEqualTo(1000); // VIP 회원의 정률 할인 금액이 1000원이 나오는지 확인
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        Member member = new Member(2L, "memberBASIC", Grade.BASIC); // 일반 회원

        int discount = discountPolicy.discount(member, 10000); // 할인 금액 확인

        // Assertions.assertThat(discount).isEqualTo(1000); // 일반 회원의 정률 할인 금액이 1000원이 나오는지 확인(실패해야 정상)
        assertThat(discount).isEqualTo(0); // 일반 회원의 정률 할인 금액이 1000원이 나오는지 확인
    }
}
