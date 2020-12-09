package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링 설정 관련 Annotation 으로 스프링 실행 시 해당 파일을 읽는다.
@Configuration
public class SpringConfig {

    // DI - Annotation 이 아닌 자바 코드로 직접 스프링 빈을 등록하는 방법

    // @Bean : 수동으로 스프링 빈을 입력하라는 의미로 아래 로직 "new MemberService()" 를 호출하여 스프링 빈에 등록한다.
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // MemberRepository : 인터페이스
    // MemoryMemberRepository : 구현체
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
