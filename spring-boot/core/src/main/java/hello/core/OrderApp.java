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
        // MemberServiceImpl 구현체를 MemberService 인터페이스로 받음
        MemberService memberService = new MemberServiceImpl();

        // OrderServiceImpl 구현체를 OrderService 인터페이스로 받음
        OrderService orderService = new OrderServiceImpl();

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
