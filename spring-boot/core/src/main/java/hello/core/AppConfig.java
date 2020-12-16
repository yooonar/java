package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 애플리케이션의 실제 동작에서 필요한 구현 객체를 생성한다.
// DIP 해결 방법 - 여기서 객체를 생성 및 연결하고, 각 구현체(OrderServiceImpl, MemberServiceImpl)에서는 실행만 한다.
public class AppConfig {

    public MemberService memberService() {
        // 생성자 주입(연결)
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        // 생성자 주입(연결)
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
