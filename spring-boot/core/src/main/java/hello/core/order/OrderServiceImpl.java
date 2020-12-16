package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    // 회원 찾기 위해 필요
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 고정 할인 정책
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 정률 할인 정책
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // DIP 위반 해결 방법 - 인터페이스에만 의존하도록 소스 변경
    // 클라이언트인 OrderServiceImpl 입장에서 보면 의존 관계를 마치 외부에서 주입하는 것 같다고 하여 DI(Dependency Injection) 의존 관계(의존성) 주입 이라고 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


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
