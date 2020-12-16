package hello.core;

import hello.core.member.*;

// 회원 테스트
public class MemberApp {

    public static void main(String[] args) {

        // 의존성 주입
        AppConfig appConfig = new AppConfig();

        // MemberService 인터페이스가 MemberServiceImpl 구현체를 선택
        // MemberService memberService = new MemberServiceImpl();
        // DI - 의존성 주입
        MemberService memberService = appConfig.memberService();

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
