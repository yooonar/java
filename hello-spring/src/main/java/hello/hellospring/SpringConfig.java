package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // jpa 에서 사용하는 엔티티 매니저
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

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
        return new MemberService(memberRepository());
    }

    // memberRepository를 스프링 빈에 등록하는 부분
    // memberService() 메소드에 인자값으로 넘어가야함
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em); // jpa를 이용한 개발
        // return new JdbcTemplateMemberRepository(dataSource); // jdbcTemplate를 이용한 개발
        // return new JdbcMemberRepository(dataSource); // jdbc를 이용한 개발
        // return new MemoryMemberRepository(); // 메모리를 이용한 개발
    }
}
