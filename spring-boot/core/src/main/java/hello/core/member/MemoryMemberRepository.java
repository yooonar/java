package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// 메모리 구현체
public class MemoryMemberRepository implements MemberRepository {

    // 회원 저장소
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        // 회원 가입
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        // 아이디 찾기
        return store.get(memberId);
    }
}
