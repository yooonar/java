package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 관례: 구현체가 하나만 있는 경우 인터페이스명 뒤에 Impl 을 붙여준다.

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
