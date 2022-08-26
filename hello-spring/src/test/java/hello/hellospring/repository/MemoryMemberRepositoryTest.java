package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 별도의 실행 순서가 없어 한 번 리포지토리에 등록되면 다음 테스트에서 오류가 발생할 수 있다.
    // 이를 방지하기 위해 각각의 테스트가 끝날 때마다 저장소를 비워주어야 한다.
    // 테스트끼리 의존 관계가 없어야 함!
    @AfterEach // 콜백 메소드! 테스트가 끝날 때마다 동작함
    public void afterEach() {
        repository.clearStore(); // 테스트가 실행되고 끝날 때마다 저장소를 한 번씩 비워줌
    }

    // 회원 저장 기능이 정상적으로 구동되는지 테스트
    @Test
    public void save() {
        Member member = new Member(); // 회원 데이터 객체 생성
        member.setName("spring"); // 회원 이름: spring
        repository.save(member); // 리포지토리에 회원 데이터 저장
        Member result = repository.findById(member.getId()).get(); // 내가 넣은 spring 회원 데이터 가져오기
        // findById() 메소드의 반환 타입은 Optional<Member>, Optional 에서 값을 꺼낼 땐 get() 메소드를 사용할 수 있음
        /*
        member.getId()를 가져올 수 있는 이유
        MemoryMemberRepository 의 save() 메소드에서 id를 생성해주었기 때문!
        member.setId(++sequence);
         */

        // 검증 방법: 저장된 데이터(result)와 내가 넣은 데이터(member)가 같은지 확인
        // 1. 텍스트 출력
        // System.out.println("result = " + (result == member));
        // 실행결과: result = true

        // 2. junit.jupiter.Assertions.assertEquals(테스트값, 기대값); 를 이용해 확인 가능
        // 이 때 성공해도 아무런 출력이 일어나지 않음, 실패 시에만 에러 메시지 출력됨
        org.junit.jupiter.api.Assertions.assertEquals(member, result);

        // 3. org.assertj.core.api.Assertions.assertThat(테스트값).isEqualTo(기대값); 을 이용해 확인 가능(더 편하게 확인할 수 있음)
        // 이 때 성공해도 아무런 출력이 일어나지 않음, 실패 시에만 에러 메시지 출력됨
        assertThat(member).isEqualTo(result);
        
        // 실무에서는 빌드 툴과 연동하여 테스트 실패 시 다음 단계로 넘어가지 않도록 조치하여 사용한다고 함
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); // spring1 회원 가입

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); // spring2 회원 가입

        // member1 회원이 있는지 확인
        Member result1 = repository.findByName("spring1").get();
        assertThat(result1).isEqualTo(member1);

        Member result2 = repository.findByName("spring2").get();
        assertThat(result2).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1); // spring1 회원가입

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); // spring2 회원가입

        List<Member> result = repository.findAll(); // 전체 회원 찾기
        assertThat(result.size()).isEqualTo(2); // 2개의 데이터가 출력되어야 함
    }
}
