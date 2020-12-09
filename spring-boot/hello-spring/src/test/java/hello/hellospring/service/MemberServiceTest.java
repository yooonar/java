package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // 서비스 필요
    MemberService memberService = new MemberService();

    // 만약 회원가입에서 이름을 "hello" 가 아닌 중복 회원 예외 메소드와 같은 "spring" 이라고 입력한다면 메모리에 계속 쌓이기 때문에 회원가입 테스트에서 오류가 날 것이다.
    // 그래서 clear 를 해줘야 한다. 현재는 Service 밖에 없기 때문에 Repository 를 불러와 clear 를 넣어줘야 한다.
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        // 메소드가 실행될 때마다 초기화
        memberRepository.clearStore();
    }

    /*
        - Given, When, Then 패턴
        보통 테스트 할 때는 given, when, then 주석을 추가하여 작업하는 것을 권장한다.
        테스트가 클 땐 각각의 영역을 보고 "어떤 데이터들로(given) 이것을 실행했을 때(when) 어떻게 되어야 한다(then)." 라는 것을 금방 알 수 있다.
     */

    @Test
    void 회원가입() { // 테스트 메소드는 "join" 이 아니라 "회원가입" 이라고 한글로 작성해도 된다.
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        // 멤버 서비스에 saveId 값이 있는지 확인
        Member findMember = memberService.findOne(saveId).get();

        // member의 이름과 service 에 있는 이름이 같은지 확인
        // staic import(import static org.assertj.core.api.Assertions.*;) 로 Assertions.assertThat() -> assertThat() 으로 변경되었다.
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
        memberService.join(member1);

        /*
        1. assertThrows 를 이용한 방법
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        =>  "() -> memberService.join(member2)" 로직을 실행하는데 IllegalStateException 이 발생해야한다는 뜻이다.
        게다가 이 assertThrows 는 리턴형이 반환되어 메시지 입력도 가능하다.
         */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        // 2. try catch 방법
        try {
            // 중복 회원이라 실패해야 정상인데 성공되는 거임
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            // MemberService.java -> validateDuplicateMember() 메소드의 Exception 메시지가 발생하는지 확인
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다..");
        }
        */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}