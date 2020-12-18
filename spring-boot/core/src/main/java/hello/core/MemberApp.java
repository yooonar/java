package hello.core;

import hello.core.member.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 회원 테스트
public class MemberApp {

    public static void main(String[] args) {

        // 스프링 문법으로 구현된 소스
        // ApplicationContext : 스프링 컨텍스트 (@Bean 관리)
        // AppConfig 에 있는 환경 설정 정보를 가지고 스프링 빈에 넣어 관리함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 스프링 컨테이너를 통해 찾아옴. getBean("메소드명", 반환타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        /*
        // 기본 자바 문법으로 구현된 소스
        // 의존성 주입
        AppConfig appConfig = new AppConfig();

        // MemberService 인터페이스가 MemberServiceImpl 구현체를 선택
        // MemberService memberService = new MemberServiceImpl();
        // DI - 의존성 주입
        MemberService memberService = appConfig.memberService();
         */

        // memberA 회원
        Member member = new Member(1L, "MemberA", Grade.VIP);

        // memberA 회원 가입
        memberService.join(member);

        // 가입 되었는지 확인
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
