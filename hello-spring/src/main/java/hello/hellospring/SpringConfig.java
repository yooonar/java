package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // MemberService를 스프링 빈에 등록하는 부분
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // memberRepository를 스프링 빈에 등록하는 부분
    // memberService() 메소드에 인자값으로 넘어가야함
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
