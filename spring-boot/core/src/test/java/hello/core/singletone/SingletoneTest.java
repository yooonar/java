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

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletoneServiceTest() {
        // new SingletoneService(); 하는 경우 private 형식이라 오류 발생
        SingletoneService singletoneService1 = SingletoneService.getInstance();
        SingletoneService singletoneService2 = SingletoneService.getInstance();

        System.out.println("singletoneService1 = " + singletoneService1);
        System.out.println("singletoneService2 = " + singletoneService2);
        /*
        둘 다 2473b9ce 로 같은 객체의 인스턴스가 반환됨
        singletoneService1 = hello.core.singletone.SingletoneService@2473b9ce
        singletoneService2 = hello.core.singletone.SingletoneService@2473b9ce
        */
        Assertions.assertThat(singletoneService1).isSameAs(singletoneService2);
        // same : 자바의 == 비교
        // equal : 자바의 equals 비교
    }
}
