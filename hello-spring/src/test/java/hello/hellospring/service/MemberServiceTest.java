package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    // 1. 인스턴스를 각각 생성하는 방법
    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 2. 인스턴스를 공통으로 사용하는 방법
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // 테스트 할 때마다 독립적으로 생성되어야 하기 때문에 beforeEach 안에 들어간다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
        // 메모리 초기화
    }

    @Test
    void 회원가입() { // 테스트 케이스는 한글도 가능
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        // 저장된 회원 가져오기
        Member findMember = memberService.findOne(saveId).get();
        // 저장된 회원이 내가 저장한 값과 같은지 확인
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        // 두번째 방법
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // assertThrows 는 try 문을 작성하는 것까진 부담스러울 때 간단하게 작성할 수 있다.
        // 기대값: () -> memberService.join(member2)을 실행할 때 IllegalStateException 이 발생해야함!
        // IllegalStateException의 값을 리턴받아 메시지가 일치하는지 비교한다.

        /*
        첫번째방법
        try { // 예외 처리
            memberService.join(member2); // 중복 회원 가입, 오류 발생 지점!
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            // validateDuplicateMember 메소드의 IllegalStateException 메시지 내용과 같아야함!
        }
        */
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
