package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach // 테스트 실행 전에 무조건 실행됨
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService(); // 인터페이스 의존
        orderService = appConfig.orderService(); // 인터페이스 의존
    }
    /*
    MemberService memberService = new MemberServiceImpl(); // 구체화 의존(DIP 위배)
    OrderService orderService = new OrderServiceImpl(); // 구체화 의존(DIP 위배)
    */

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);// VIP 회원
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 100000); // 10000원짜리 itemA 구매
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000); // 1000원 할인된 것이 맞는지 확인

    }
}
