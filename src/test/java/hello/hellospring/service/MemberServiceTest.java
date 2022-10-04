package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*
    MemberService memberService = new MemberService(); // 자동완성 단축키 뭐였더라 .. : Ctrl + Shift + Enter
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
     */

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 동일한 memberRepository를 사용하기 위한 코드 : DI
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // Shift + F10 : 이전에 실행했던 명령을 다시 실행해줌  ex. 만약 클래스 전체 실행 누르고 Shift+F10이면 다시 클래스 전체 실행
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test  // 저장 잘 되는지
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

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



/* Ctrl + Shift + / : 여러줄 주석 처리
   Shift + 방향키 : 행 선택
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.") ;
        }
*/

        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}