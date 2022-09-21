package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);// VIP 회원
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 100000); // 10000원짜리 itemA 구매
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 1000원 할인된 것이 맞는지 확인

    }
}
