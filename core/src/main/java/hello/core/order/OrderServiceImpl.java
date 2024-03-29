package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // final = 무조건 기본 할당 되어 있어야 함(생성자를 통해 할당함)
    private final MemberRepository memberRepository; // 인터페이스에 의존
    private final DiscountPolicy discountPolicy; // 인터페이스에 의존

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원 찾기
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정할인 정책. 인터페이스 + 구체화에 의존(DIP 위배)
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정률할인 정책. 인터페이스 + 구체화에 의존(DIP 위배)
    private DiscountPolicy discountPolicy; // 인터페이스 의존
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 찾기
        int discountPrice = discountPolicy.discount(member, itemPrice); // 회원 등급에 따른 할인 금액

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성
    }
}
