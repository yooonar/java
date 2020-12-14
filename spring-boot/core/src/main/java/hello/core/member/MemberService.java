package hello.core.member;

public interface MemberService {

    // 회원 가입
    void join(Member member);

    // 아이디 찾기
    Member findMember(Long memberId);
}
