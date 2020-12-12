package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
    인터페이스가 인터페이스를 받을 땐 extends
    JpaRepository<타입, PK>
    Spring Data JPA 가 구현체를 자동으로 만들어주기 때문에 SpringConfig 에서 가져다가 쓰면 된다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
        JpaRepository 가 save, findAll, findById 를 자동으로 생성해준다.
        JpaRepository는 기본 CRUD 를 제공해준다.
        name 컬럼 같은 경우는 회사마다 사용하는 이름이 다르기 때문에 자동으로 생성해줄 수 없다. 그래서 findByName 을 만들어주는 것이다.

        findByName(컬럼명) : SELECT m FROM Member m WHERE m.name = ? JPQL 로 만들어준다.

        다른 조건이 AND 조건으로 추가된다면?
        findByNameAndId(String name, Long id);
     */
    @Override
    Optional<Member> findByName(String name);
}
