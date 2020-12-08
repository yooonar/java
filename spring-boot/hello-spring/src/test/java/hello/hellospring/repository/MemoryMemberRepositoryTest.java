package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


// 테스트는 서로 의존 관계 없이 각각 실행이 가능하기 때문에 클래스 단위로 테스트 했을 때 실행 순서에 관계 없이 테스트가 가능하도록 설계해주어야 한다.
// 작업 순서는 테스트를 먼저 하고 개발을 할 수도 있는데 이를 TDD(테스트 주도 개발) 라고 한다.
class MemoryMemberRepositoryTest {

    // 테스트 코드 작성
    MemoryMemberRepository repository = new MemoryMemberRepository();
    // MemberRepository repository = new MemoryMemberRepository();

    // @AfterEach : 메소드(save(), findByName(), findAll()) 실행이 끝날 때마다 실행 (callback method)
    @AfterEach
    public void afterEach() {
        // 테스트는 순서가 없기 때문에 메소드 실행이 끝날 때마다 저장소를 비우지 않으면 테스트 오류가 발생할 수 있다.
        // 메소드 실행이 끝날 때마다 저장소를 비운다.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional 데이터를 가져올 때 .get() 을 사용 가능함(실제 소스에서는 사용 안하는 편이지만 테스트 코드에서는 사용해도 무방)
        Member result = repository.findById(member.getId()).get();

        // 가져온 데이터 result 랑 member 랑 똑같은지 비교함
        System.out.println("result = " + (result == member));
        // output: result = true

        /* junit 의 Assertions */
        // 내가 테스트하는 member 가 result 와 같은지 확인
        // Assertions.assertEquals(테스트값, 기대값);
        //Assertions.assertEquals(member, result);

        // 일부러 실패하는 값
        // Assertions.assertEquals(member, null);
        /*
        org.opentest4j.AssertionFailedError:
        Expected :hello.hellospring.domain.Member@4e41089d <- 기대값
        Actual   :null <- 실제값
         */

        // org.assertj.core.api 의 방식을 자주 쓴다.
        // Assertions.assertThat(테스트값).isEqualTo(기대값);
        // Assertions.assertThat(member).isEqualTo(result);

        // Assertions. 는 상단에 import static org.assertj.core.api.Assertions.*; 해주면 생략 가능하다.
        assertThat(member).isEqualTo(result);

        // assertThat(member).isEqualTo(null);
        /*
        org.opentest4j.AssertionFailedError:
        Expecting:
         <hello.hellospring.domain.Member@1198b989>
        to be equal to:
         <null>
        but was not.
        Expected :null <- 실제값
        Actual   :hello.hellospring.domain.Member@1198b989 <- 기대값
         */
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 변경 전 : Optional<Member> result = repository.findByName("spring1");
        // .get() 을 쓰면 Optional 을 벗겨 Optional<Member> -> Member 형식으로 만들어줄 수 있다.
        // 변경 후 : Member result = repository.findByName("spring1").get();

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

        // 오류일 때
        Member result2 = repository.findByName("spring2").get();
        // assertThat(result2).isEqualTo(member1);
        /*
        org.opentest4j.AssertionFailedError:
        Expecting:
         <hello.hellospring.domain.Member@8909f18>
        to be equal to:
         <hello.hellospring.domain.Member@79ca92b9>
        but was not.
        Expected :hello.hellospring.domain.Member@79ca92b9
        Actual   :hello.hellospring.domain.Member@8909f18
         */
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 정상
        // result 의 개수가 2개면
        assertThat(result.size()).isEqualTo(2);
        // Process finished with exit code 0

        // 오류
        // assertThat(result.size()).isEqualTo(3);
        /*
        org.opentest4j.AssertionFailedError:
        Expecting:
         <2>
        to be equal to:
         <3>
        but was not.
        Expected :3
        Actual   :2
         */
    }
}
