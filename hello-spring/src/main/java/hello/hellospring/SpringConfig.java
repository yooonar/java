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

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        // SpringDataJpa에서 사용하는 리포지토리
        // 그냥 주입(인젝션) 받으면 SpringDataJPA가 자동으로 등록해준다.
        this.memberRepository = memberRepository;
    }

    /*
    // jpa 에서 사용하는 엔티티 매니저
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    /*
    // jdbc, jdbcTemplate 에서 사용하는 DataSource
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    // MemberService를 스프링 빈에 등록하는 부분
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // SpringDataJpa 에서 사용
        // return new MemberService(memberRepository()); // memory, jdbc, jdbcTemplate, jpa 에서 사용
    }
/*
    // 메소드 소요시간 계산하는 부분(AOP를 이용해 공통으로 호출)
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
*/
/*
// memory, jdbc, jdbcTemplate, jpa 에서만 사용
    // memberRepository를 스프링 빈에 등록하는 부분
    // memberService() 메소드에 인자값으로 넘어가야함
    @Bean
    public MemberRepository memberRepository() {
        // return new JpaMemberRepository(em); // jpa를 이용한 개발
        // return new JdbcTemplateMemberRepository(dataSource); // jdbcTemplate를 이용한 개발
        // return new JdbcMemberRepository(dataSource); // jdbc를 이용한 개발
        // return new MemoryMemberRepository(); // 메모리를 이용한 개발
    }
 */
}
