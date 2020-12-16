package hello.core.member;

// 회원 서비스 구현체
public class MemberServiceImpl implements MemberService {

    // 회원 데이터
    // memberRepository 는 MemberRepository 추상화에도 의존하고 MemoryMemberRepository 구체화에도 의존한다는 문제점이 있음
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // DIP 위반 해결 방법 - 인터페이스에만 의존하도록 소스 변경
    // 클라이언트인 MemberServiceImpl 입장에서 보면 의존 관계를 마치 외부에서 주입하는 것 같다고 하여 DI(Dependency Injection) 의존 관계(의존성) 주입 이라고 한다.
    private final MemberRepository memberRepository;

    // DIP 해결 방법 - 생성자를 통해 memberRepository 에 들어가는 형식을 지정함 AppConfig.java 에서 관리
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

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
