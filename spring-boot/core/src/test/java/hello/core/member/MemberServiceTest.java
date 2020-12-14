package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given: ~ 환경이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when: ~ 이렇게 하면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then: ~ 이렇게 된다. (검증)
        // Assertions 는 org.assertj.core.api.Assertions; 를 사용
        // member 가 findMember 랑 같은지 확인
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
