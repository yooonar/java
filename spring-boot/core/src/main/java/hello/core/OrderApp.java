package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        // MemberServiceImpl 구현체를 MemberService 인터페이스로 받음
        // MemberService memberService = new MemberServiceImpl();
        // DI - 의존성 주입
        MemberService memberService = appConfig.memberService();

        // OrderServiceImpl 구현체를 OrderService 인터페이스로 받음
        // OrderService orderService = new OrderServiceImpl();
        // DI - 의존성 주입 방식
        OrderService orderService = appConfig.orderService();

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
