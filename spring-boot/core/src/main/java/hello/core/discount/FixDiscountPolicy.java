package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인

    @Override
    public int discount(Member member, int price) {

        // 비교할 때 Enum 타입은 == 사용
        if(member.getGrade() == Grade.VIP) {
            // VIP
            return discountFixAmount;
        } else {
            // BASIC
            return 0;
        }
    }
}
