package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*; // List, Optional, HashMap

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에서는 동시성 이슈로 HashMap 보다는 ConcurrentHashMap 을 더 선호한다.
    private static long sequence = 0L; // 0, 1, 2, ... 키 값을 생성
    // 실무에서는 동시성 이슈로 long 형도  AtomicLong 을 더 선호한다.

    @Override
    public Member save(Member member) {
        // 이름은 고객이 직접 입력하기 때문에 save()로 넘어올 때부터 있다.
        member.setId(++sequence); // sequence 하나 증가시킨 후 아이디 값을 넣어 세팅해준다.
        store.put(member.getId(), member); // store 에 put 시킨다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환될 가능성이 있다면 Optional.ofNullable() 로 감싸서 리턴한다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // loop 로 돌리면서
                .filter(member -> member.getName().equals(name)) // member.getName()이 파라미터로 넘어온 name 과 일치하는 값이 있는지 찾기
                .findAny(); // 끝까지 찾았을 때 하나라도 찾으면 그 값을 반환, 없으면 Optional 에 null 이 포함되어 반환
    }

    @Override
    public List<Member> findAll() {
        // store 는 Map 형식이지만 실무에서는 loop 돌리기도 편해서 List 가 많이 쓰임
        return new ArrayList<>(store.values()); // store.values() == Member
    }
}
