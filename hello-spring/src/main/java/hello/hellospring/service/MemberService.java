package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // 1. 인스턴스를 직접 생성하는 방법
    // 서비스 내에서 생성하면 테스트 할 때 다른 인스턴스로 만들어야 해서 문제가 발생할 수 있다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 2. 외부에서 인스턴스를 받아오는 방법
    // 하나의 인스턴스를 이용하기 위해 외부에서 리포지토리를 넣어주도록 변경
    // 이렇게 MemberService 자기 자신이 직접 생성하지 않고 외부에서 들여오는 것을 의존성 주입(Dependency Injection)이라고 한다!
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // 중복 회원 검증
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 특정 회원 조회(없을 수도 있음)
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
