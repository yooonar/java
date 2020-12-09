package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService { // cmd + shift + T : 테스트 케이스 자동 작성

    // Service 쪽 용어는 비즈니스에 의존적으로 설계함 - 비개발자가 와서 봐도 무슨 기능인지 알 수 있도록
    // Repository 쪽 용어는 기계적(개발)으로 설계함

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        // 아래와 같이 함수 안에 따로 로직이 있는 경우 따로 메소드로 뽑는 것이 좋다. 단축키: 영역 드래그 + option + T -> Extract Method
        // validateDuplicateMember() 메소드로 새로 추가됨
        validateDuplicateMember(member); // 중복 회원 검증

        /*
        // 같은 이름이 있는 중복 회원은 가입 불가
        Optional<Member> result = memberRepository.findByName(member.getName());

        // 데이터가 null 일 가능성이 있다면 보통 Optional 로 감싸준다.
        // Optional<Member> : 데이터를 Optional 이 감싸고 있는 형태가 되는데 이 경우 Optional 내부에 있는 함수(ifPresent)를 사용할 수 있어 편리하다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        // orElseGet : 값이 있으면 꺼내고 없으면 해당 메소드를 실행해라.

        // 위와 같은 문장 Optional<Member> result = memberRepository.findByName(member.getName()); 에서
        // Optional 을 바로 반환하는 게 별로 좋진 않다.
        // 아래와 같이 권장한다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        // 어차피 memberRepository.findByName(member.getName()) 를 반환하면 Optional 이기 때문에 바로 ifPresent 를 사용할 수 있다.

        */
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
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
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
