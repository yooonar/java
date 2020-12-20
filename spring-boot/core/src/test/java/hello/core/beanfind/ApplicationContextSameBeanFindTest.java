package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    // SameBeanConfig.class 만 가지고 실행함
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {

        /*
        스프링이 memberRepository1() 인지 memberRepository2() 인지 알 수 없어 NoUniqueBeanDefinitionException 발생

        org.springframework.beans.factory.NoUniqueBeanDefinitionException:
            No qualifying bean of type 'hello.core.member.MemberRepository' available:
                expected single matching bean but found 2: memberRepository1,memberRepository2
         */
        // 타입만 지정해서 중복 오류 발생
        // MemberRepository bean = ac.getBean(MemberRepository.class);

        // 익셉션이 발생해야 정상
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면 빈 이름을 지정한다.")
    void findBeanByName() {
        // memberRepository1() 메소드 지정
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);

        // memberRepository1 이 MemberRepository 의 인스턴스냐?
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    // static 쓴 이유 : ApplicationContextSameBeanFindTest 안에서만 사용
    @Configuration
    static class SameBeanConfig {

        // 인스턴스 클래스 타입이 같은 메소드 1
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        // 인스턴스 클래스 타입이 같은 메소드 2
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
