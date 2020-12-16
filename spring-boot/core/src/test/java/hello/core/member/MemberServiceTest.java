package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    // 각 테스트가 돌기 전에 무조건 실행
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    // MemberService 인터페이스가 MemberServiceImpl 구현체를 선택
    // MemberService memberService = new MemberServiceImpl();

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
