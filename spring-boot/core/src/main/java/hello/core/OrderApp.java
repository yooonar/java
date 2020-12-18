package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        // 스프링 문법으로 구현된 소스
        // ApplicationContext : 스프링 컨텍스트 (@Bean 관리)
        // AppConfig 에 있는 환경 설정 정보를 가지고 스프링 빈에 넣어 관리함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 스프링 컨테이너를 통해 찾아옴. getBean("메소드명", 반환타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        /*
        // 기본 자바 문법으로 구현된 소스
        AppConfig appConfig = new AppConfig();

        // MemberServiceImpl 구현체를 MemberService 인터페이스로 받음
        // MemberService memberService = new MemberServiceImpl();
        // DI - 의존성 주입
        MemberService memberService = appConfig.memberService();

        // OrderServiceImpl 구현체를 OrderService 인터페이스로 받음
        // OrderService orderService = new OrderServiceImpl();
        // DI - 의존성 주입 방식
        OrderService orderService = appConfig.orderService();
         */

        // 회원 저장
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        // 주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        // output: order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}

        System.out.println("order.calculatePrice = " + order.calculatePrice());
        // output: order.calculatePrice = 9000

    }
}
