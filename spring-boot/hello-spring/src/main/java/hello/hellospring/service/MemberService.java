package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service : 스프링이 MemberService 를 생성할 때 Service 로 인지하여 스프링 컨테이너에 등록하고, 생성자를 호출한다.
// @Service // DI - 자바 소스로 등록하기 위해 주석 처리
// @Transactional : 데이터를 저장하거나 변경할 때는 항상 트랜잭션이 필요하다.
@Transactional
public class MemberService { // cmd + shift + T : 테스트 케이스 자동 작성

    // Service 쪽 용어는 비즈니스에 의존적으로 설계함 - 비개발자가 와서 봐도 무슨 기능인지 알 수 있도록
    // Repository 쪽 용어는 기계적(개발)으로 설계함

    /*
        private final MemberRepository memberRepository = new MemoryMemberRepository();
        위와 같이 MemoryMemberRepository를 인스턴로 생성했을 때 기능 자체에는 문제가 없지만
        테스트 할 때 서로 다른 인스턴스를 바라보기 때문에 문제가 발생할 수 있다.
        그렇기 때문에 테스트 할 때도 같은 Repository 를 사용할 수 있도록 아래와 같이 변경해야한다.
     */
    private final MemberRepository memberRepository;

    /*
        @Autowired : 의존성 주입
        MemberService 에도 MemberRepository 가 필요하며, 외부에서 불러오고 있다.
        스프링이 MemberService 를 생성할 때 스프링에서 스프링 빈(컨테이너)에 저장되어있는 공통의 memberRepository 를 불러와 가져다준다.
        MemberRepository 의 구현체인 MemoryMemberRepository 를 Service 에 주입해준다.

        memberRepository 를 기존처럼 new 로 직접 생성해주는 것이 아니라 외부에서 넣어주도록 선언한다.
        MemberService 입장에서는 자기가 직접 Repository 를 선언하지 않는다. = Dependency Injection 의존성 주입
     */
    // @Autowired // DI - 자바 소스로 등록하기 위해 주석 처리
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis();

        try {
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
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            // 회원 가입까지 걸리는 시간
            System.out.println("join = " + timeMs + "ms");
        }

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
