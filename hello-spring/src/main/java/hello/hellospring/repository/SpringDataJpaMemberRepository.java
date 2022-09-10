package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // SpringDataJpa가 JpaRepository<Member, Long> 를 받고 있으면
    // SpringDataJpaMemberRepository가 구현체를 자동으로 만들어 스프링빈에도 자동으로 등록해준다.
    // JpaRepository 가 기본 CRUD 등과 같은 기능을 제공해주고, 단순 조회 메소드도 자동으로 제공해준다.

    // findByName는 공통화 할 수 없기 때문에 별도로 생성해준다.
    // select m from Member m where m.name = ? 쿼리로 작동함
    @Override
    Optional<Member> findByName(String name);
}
