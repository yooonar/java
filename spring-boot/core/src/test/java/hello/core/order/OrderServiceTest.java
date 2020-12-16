package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    // 각 테스트가 돌기 전에 무조건 실행
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    // MemberServiceImpl 구현체를 MemberService 인터페이스로 받음
    // MemberService memberService = new MemberServiceImpl();

    // OrderServiceImpl 구현체를 OrderService 인터페이스로 받음
    // OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        // 회원 저장
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // VIP 등급인 memberA 에게 1000원 할인을 해주는지 확인
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
