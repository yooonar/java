package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // 같은 이름이 있는 중복 회원은 가입 불가
        Optional<Member> result = memberRepository.findByName(member.getName());

        // 데이터가 null 일 가능성이 있다면 보통 Optional 로 감싸준다.
        // Optional<Member> : 데이터를 Optional 이 감싸고 있는 형태가 되는데 이 경우 Optional 내부에 있는 함수(ifPresent)를 사용할 수 있어 편리하다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        // orElseGet : 값이 있으면 꺼내고 없으면 해당 메소드를 실행해라.

        memberRepository.save(member);
        return member.getId();
    }
}
