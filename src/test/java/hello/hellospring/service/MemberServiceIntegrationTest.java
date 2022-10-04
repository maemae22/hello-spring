package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 테스트에서 @Transactional 어노테이션 달면, 자동으로 트랙잭션 생성 후 롤백까지 해줘서, 테스트 반복수행해도 문제 없음 !
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test  // 저장 잘 되는지
    // @Commit
    // @Commit  : 끝나면 Commit 하게 만들어버리는 어노테이션
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring100");

        // when
        Long saveId = memberService.join(member); // 리턴값 : Ctrl+Alt+V

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());  // Alt + Enter : static import로 넘기기
    }

    // 예외케이스 테스트 : 중복회원 검증 테스트
    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();  // Alt + Shift + 방향키 : 행 이동
        member1.setName("spring");

        Member member2 = new Member();  // Shift + F6 : 한번에 수정하기 ?
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 리턴값 : Ctrl+Alt+V

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // then
    }

}