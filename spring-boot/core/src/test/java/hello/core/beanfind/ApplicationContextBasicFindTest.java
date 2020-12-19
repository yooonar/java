package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        /*
        System.out.println("memberSeervice = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        memberSeervice = hello.core.member.MemberServiceImpl@3ddc6915
        memberService.getClass() = class hello.core.member.MemberServiceImpl
         */

        // memberService 가 MemberServiceImpl 의 인스턴스냐? -> 성공
        // Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        /*
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        memberService = hello.core.member.MemberServiceImpl@6986852
        memberService.getClass() = class hello.core.member.MemberServiceImpl
         */

        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        // MemberServiceImpl 구체화 타입으로 조회도 가능하다. (별로 좋은 방법은 아님)
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        // 등록되지 않은 빈 이름(xxxx)이면 NoSuchBeanDefinitionException 발생
        // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxx' availables
        // MemberService xxxx = ac.getBean("xxxx", MemberService.class);

        // jUnit5에서 소스가 복잡해짐
        // ac.getBean("xxxx", MemberService.class) 로직을 실행했을 때 NoSuchBeanDefinitionException.class 예외가 터져야 성공
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }
}
