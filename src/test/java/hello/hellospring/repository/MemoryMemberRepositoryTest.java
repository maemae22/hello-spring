package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 끝날 때마다 이 메서드가 동작하도록 하는 어노테이션 (콜백 메서드)
    public void afterEach() {
        repository.clearStore(); // 테스트가 끝날 때마다 저장소를 다 지우게끔 만듦
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");  // 커서가 중간에 있을 때 바로 아래줄로 내려오는 방법 : Ctrl + Shift + Enter

        repository.save(member);

        // .get() : Optional에서 값을 꺼내오는 방법 (findById의 반환타입이 Optional)
        Member result = repository.findById(member.getId()).get();

        // System.out.println("결과 : " + (result == member));
        // Assertions.assertEquals(member, result);  // 내가 만든 member와 찾아온 result 객체가 동일한지 확인
        // Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);  // 윗줄에서 static import 하면 다음부터는 바로 assertThat 사용 가능

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  // Shift + F6 : 객체 rename
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
