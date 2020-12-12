package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceApp;
import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 스프링 설정 관련 Annotation 으로 스프링 실행 시 해당 파일을 읽는다.
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    // JPA 를 위해 EntityManager 선언
    EntityManager em;

    // 스프링부트가 application.properties 에 등록한 h2 db 설정을 보고 dataSource 를 만듦
    private DataSource dataSource;

    // 스프링부트에 주입
    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.dataSource = dataSource;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    // DI - Annotation 이 아닌 자바 코드로 직접 스프링 빈을 등록하는 방법

    // @Bean : 수동으로 스프링 빈을 입력하라는 의미로 아래 로직 "new MemberService()" 를 호출하여 스프링 빈에 등록한다.
    @Bean
    public MemberService memberService() {

        // Spring Data JPA 에서는 그냥 리턴
        return new MemberService(memberRepository);

        // 메모리, h2 db, jdbc template, JPA 에서는 memberRepository 메소드를 호출
        // return new MemberService(memberRepository());
    }

    // MemberRepository : 인터페이스
    // MemoryMemberRepository : 구현체
    @Bean
    public MemberRepository memberRepository() {

        // Spring Data JPA

        // JPA
        return new JpaMemberRepository(em);

        // jdbc template
        // return new JdbcTemplateMemberRepository(dataSource);

        // h2 db
        // return new jdbcMemberRepository(dataSource);

        // 메모리
        // return new MemoryMemberRepository();
    }

    /*
    // AOP 를 스프링빈에 직접 등록
    @Bean
    public TimeTraceApp timeTraceApp() {
        return new TimeTraceApp();
    }
    */
}
