package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        /*
        MemberService memberService = new MemberServiceImpl(); // 구현체 의존(DIP 위배)
        OrderService orderService = new OrderServiceImpl(); // 구현체 의존(DIP 위배)
        */
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP); // VIP 등급 회원
        memberService.join(member); // 회원가입

        Order order = orderService.createOrder(memberId, "itemA", 10000); // 10000원짜리 itemA 구매

        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
