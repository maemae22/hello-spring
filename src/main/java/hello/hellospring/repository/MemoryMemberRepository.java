package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements  MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // name은 고객이 회원가입 시 적기 때문에, 이름은 넘어와있는 상태임
        member.setId(++sequence);
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // return store.get(id);  Map 객체에서 id로 member를 찾아오면 됨. 근데 null이 될 수 있으니까 아래처럼 감쌈
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // 람다식. store에서 member의 name이 파라미터에서 넘겨온 name이랑 같은지 검색
                .filter(member -> member.getName().equals(name))
                .findAny(); // 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // Map 객체를 List 객체로 바꾸는 방법
    }

    // 테스트 코드 오류 방지
    public void clearStore() {
        store.clear(); // store를 싹 비움
    }
}
