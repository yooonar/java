package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    /*
        JPA 는 EntityManager 로 모든게 동작한다.
        build.gradle 에서 data-jpa 라이브러리를 받고 application.properties 에서 세팅을 하면
        스프링 부트가 자동으로 EntityManager 를 생성한다.
        현재 DB랑 연결해서 만들어진 EntityManager 를 인젝션 받아 사용하면 된다.
     */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // .persist(데이터) : insert 쿼리 만들어서 DB에 입력하고 id 값(setId)까지 자동으로 작업해줌. 리턴 값 없음
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // .find(조회할 타입, 식별자 PK) : id 조회
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        // PK 가 아닌 경우 createQuery 를 통해 JPQL 쿼리를 작성해야한다.
        // JPQL : SELECT m FROM Member m WHERE m.name = :name
        List<Member> result = em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        // result.stream().findAny() : 결과를 하나만 찾기
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // PK 가 아닌 경우 createQuery 를 통해 JPQL 쿼리를 작성해야한다.
        // JPQL : SELECT m FROM Member m
        // SELECT m : Entity 자체를 셀렉트 함
        // getResultList() : 리스트 형식으로 반환
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}
