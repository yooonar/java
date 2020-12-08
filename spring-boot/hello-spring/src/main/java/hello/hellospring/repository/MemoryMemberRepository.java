package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store.get(id) 이 null 일경우가 있을 수 있으니 Optional.ofNullable 로 감싸준다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // stream() = store.values() 를 반복
                .filter(member -> member.getName().equals(name)) // member.getName() 이 parameter 로 넘어온 name 과 같은지 확인(람다식)
                .findAny(); // 하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        // store.values() = Map<Long, Member> 의 Member
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        // store 비우기
        store.clear();
    }
}
