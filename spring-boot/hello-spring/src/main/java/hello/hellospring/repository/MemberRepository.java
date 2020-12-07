package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원 정보 저장
    Member save(Member member);

    // Optional - Java 8 에서 사용함.
    // id 가 없을 경우 Null 을 반환할 수 있는데, Null 을 처리하는 방법에서 Null 을 그대로 반환하기 보다는 Optional 로 감싸서 반환하는 것을 많이 선호한다.

    // 아이디 찾기
    Optional<Member> findById(Long id);

    // 이름 찾기
    Optional<Member> findByName(String name);

    // 모든 회원 리스트 찾기
    List<Member> findAll();
}
