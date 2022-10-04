package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /*
    private DataSource dataSource;

    @Autowired  // DI : 생성자 주입
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /* JPA
    private EntityManager em ;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

/*
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());      // Ctrl + P : parameter 정보를 알 수 있음
    }
*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);      // Ctrl + P : parameter 정보를 알 수 있음
    }

/*    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
*/

/*
    @Bean  // AOP를 Spring Bean에 직접 등록
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
*/
}
