package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 관례: 구현체가 하나만 있는 경우 인터페이스명 뒤에 Impl 을 붙여준다.

    private final MemberRepository memberRepository; // 인터페이스만 있음(추상화에만 의존)

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository; // 생성자를 통해 구현체를 결정함
    }

    /*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    실제 할당하는 부분이 구현체(MemoryMemberRepository)를 의존하고 있다.
    결과적으로 MemberServiceImpl는 MemberRepository(추상화), MemoryMemberRepository(구체화) 둘 다 의존하게 되어 DIP를 위반하고 있다.
    */

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
