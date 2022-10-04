package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service
public class MemberService {
    // 클래스 안에서 Ctrl+Shift+T : 테스트 자동 생성

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자 생성 : Alt+Ins => Constructor
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 1. 회원 가입 (이름 중복 불가능)
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X
        // 단축키 : Ctrl + Alt + V - 리턴값 자동으로 만들어줌  (맥 옵션=윈도Alt / 맥Command=윈도Ctrl : 보통 이럼)
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent( m -> {   // ifPresent : null이 아니라 어떤 값이 있으면 로직이 동작 (Optional이여서 가능)
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
         */
        // Refactor 단축키 : Ctrl + Alt + Shift + T
        // Extract Method 단축키 : Ctrl+Alt+M

       /* long start = System.currentTimeMillis();
        try {
            validateDuplicateMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println(" join timeMs = " + timeMs + "ms");
        }*/

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 중복(이름 중복) 검증하는 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }


    // 2. 전체 회원 조회
    public List<Member> findMembers() {
        // long start = System.currentTimeMillis();
        return memberRepository.findAll();
    }

    // 3. id로 member 찾기
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
