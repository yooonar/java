package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given 이렇게 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP); // VIP 회원

        // when 이런 상황에서
        memberService.join(member); // 가입
        Member findMember = memberService.findMember(1L); // 같은 회원인지 조회

        // then 이렇게 되어야 한다.
        Assertions.assertThat(member).isEqualTo(findMember); // 가입한 회원이 조회한 회원과 같은 회원인지 검증
    }
}
