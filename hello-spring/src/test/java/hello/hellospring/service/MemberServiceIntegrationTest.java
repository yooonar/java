package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 재실행했을 때 오류가 나지 않도록 스프링부트가 제공함(메소드 실행마다 트랜잭션을 걸어 테스트 끝나면 롤백함)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // @Commit // 실제 실행
    void 회원가입() { // 테스트 케이스는 한글도 가능
        // given
        Member member = new Member();
        member.setName("spring");

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
    }
}
