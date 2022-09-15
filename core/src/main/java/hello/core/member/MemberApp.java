package hello.core.member;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP); // 회원 정보 입력
        memberService.join(member); // 회원 가입

        Member findMember = memberService.findMember(1L); // 가입한 회원
        System.out.println("new Member = " + member);
        System.out.println("find Member = " + findMember);

    }
}
