package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면, 저장된 회원이 반환
    // Optional : (null 값을 처리하는 방법) id나 name으로 회원을 찾았을 때 null 이면, Optional로 null을 감싸서 반환함
    Optional<Member> findById(Long id); // id로 회원찾기
    Optional<Member> findByName(String name); // name으로 회원찾기
    List<Member> findAll(); // 전체 회원 검색
}
