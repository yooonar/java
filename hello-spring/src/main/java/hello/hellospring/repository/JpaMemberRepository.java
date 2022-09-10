package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em; // JPA는 모든 걸 엔티티 매니저로 동작함

    public JpaMemberRepository(EntityManager em) {
        // 스프링부트가 JPA 관련 세팅을 바탕으로 엔티티 매니저를 자동으로 만들어준다. 이걸 주입(인젝션) 받으면 된다.
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // persist = 영구 저장, 리턴값 없음
        // insert 쿼리를 만들어서 넣어주고, setId 까지 넣어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // em.find(조회타입, 식별자pk) pk인 경우에만!
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // jpql 쿼리문(테이블이 아닌 객체(엔티티)를 대상으로 쿼리를 날리면 SQL로 변환이 된다.)
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) // name 값 입력
                .getResultList();

        return result.stream().findAny(); // 하나만 반환
    }

    @Override
    public List<Member> findAll() {
        // jpql 쿼리문(테이블이 아닌 객체(엔티티)를 대상으로 쿼리를 날리면 SQL로 변환이 된다.)
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList();

        /*
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        */
    }
}
