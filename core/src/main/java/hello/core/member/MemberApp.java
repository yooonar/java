package hello.core.member;

import hello.core.AppConfig;

public class MemberApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        // MemberService memberService = new MemberServiceImpl(); // 구현체 의존(DIP 위배)
        Member member = new Member(1L, "memberA", Grade.VIP); // 회원 정보 입력
        memberService.join(member); // 회원 가입

        Member findMember = memberService.findMember(1L); // 가입한 회원
        System.out.println("new Member = " + member);
        System.out.println("find Member = " + findMember);

    }
}
