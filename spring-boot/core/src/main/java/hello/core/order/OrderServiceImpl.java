package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 찾기 위해 필요
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 고정 할인 정책
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 회원 찾기
        Member member = memberRepository.findById(memberId);

        // 할인 금액
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 생성한 주문 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
