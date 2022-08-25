package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*; // Optional, List

public interface MemberRepository {
    Member save(Member member); // 저장소에 회원 저장
    Optional<Member> findById(Long id); // 회원 조회
    Optional<Member> findByName(String name); // 회원 이름 조회
    /*
    Optional: java8에 들어있는 기능.
    데이터가 없을 때 Null을 처리하는 방법 중 Null을 그대로 반환하기 보다 optional로 감싸서 반환해주는 것을 더 많이 선호한다.
    */
    List<Member> findAll(); // 전체 회원 조회
}
