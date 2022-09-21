package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // 테스트 실행 전에 무조건 실행됨
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService(); // 인터페이스 의존
    }

    // MemberService memberService = new MemberServiceImpl(); // 구체화 의존

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
