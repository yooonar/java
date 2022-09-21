package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원 찾기
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정할인 정책. 인터페이스 + 구체화에 의존
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정률할인 정책. 인터페이스 + 구체화에 의존
    private DiscountPolicy discountPolicy; // 인터페이스 의존

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 찾기
        int discountPrice = discountPolicy.discount(member, itemPrice); // 회원 등급에 따른 할인 금액

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성
    }
}
