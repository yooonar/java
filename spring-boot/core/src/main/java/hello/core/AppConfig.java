package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration : 스프링에서 사용하는 애플리케이션 설정(구성) 정보
@Configuration
// 애플리케이션의 실제 동작에서 필요한 구현 객체를 생성한다.
// DIP 해결 방법 - 여기서 객체를 생성 및 연결하고, 각 구현체(OrderServiceImpl, MemberServiceImpl)에서는 실행만 한다.
public class AppConfig {

    // @Bean : 스프링 컨테이너에 등록됨
    @Bean
    public MemberService memberService() {
        // 생성자 주입(연결)
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    // 소스 중복을 없애고 하나로 만들어 나중에 DB가 바뀌더라도 이 부분만 수정하면 되도록 함.
    public MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        // 생성자 주입(연결)
        return new OrderServiceImpl(getMemberRepository(), discountPolicy());
    }

    @Bean
    // 소스 중복을 없애고 하나로 만들어 나중에 할인 정책이 바뀌더라도 이 부분만 수정하면 되도록 함.
    public DiscountPolicy discountPolicy() {
        // 정액 할인 방식
        // return new FixDiscountPolicy();

        // 정률 할인 방식
        return new RateDiscountPolicy();
    }
}
