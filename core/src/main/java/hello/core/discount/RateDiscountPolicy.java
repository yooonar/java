package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10; // 10% 할인

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) { // VIP 등급인 경우
            return price * discountPercent / 100; // 10% 할인
        } else {
            return 0;
        }
    }
}
