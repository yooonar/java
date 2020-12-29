package hello.core.singletone;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletoneTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조 값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        /*
        객체를 생성할 때마다 71d15f18, 17695df3 로 바뀜
        memberService1 = hello.core.member.MemberServiceImpl@71d15f18
        memberService2 = hello.core.member.MemberServiceImpl@17695df3
        */

        // memberService1 != memberService2 여야 정상
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

        // memberService 안에 있는 MemberServiceImpl, memberRepository 각각 생성되기 때문에 총 4개의 객체가 생성된다.
    }
}
