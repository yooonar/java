package hello.core.member;

// 회원 서비스 구현체
public class MemberServiceImpl implements MemberService {

    // 회원 데이터
    // memberRepository 는 MemberRepository 추상화에도 의존하고 MemoryMemberRepository 구체화에도 의존한다는 문제점이 있음
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        // 회원 가입
        // 다형성에 의해 MemberRepository 인터페이스가 아닌 MemoryMemberRepository.save() 가 호출된다.
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        // 아이디 찾기
        return memberRepository.findById(memberId);
    }
}
