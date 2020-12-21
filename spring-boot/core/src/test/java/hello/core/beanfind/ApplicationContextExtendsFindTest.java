package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate() {
        // 오류 발생
        // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

        // NoUniqueBeanDefinitionException 오류가 발생해야 정상
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회 시 자식이 둘 이상 있으면 빈 이름을 지정하면 오류가 발생하지 않는다.")
    void findBeanByParentTypeBeanName() {

        // 구현 객체 rateDiscountPolicy 를 지정하면 오류가 발생하지 않는다.
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);

        // rateDiscountPolicy 가 RateDiscountPolicy 의 인스턴스냐?
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    // 별로 좋은 방법은 아님
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        // RateDiscountPolicy 타입을 바로 지정
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        // DiscountPolicy 타입이 2개인지 확인
        assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
            /*
            key = rateDiscountPolicy value = hello.core.discount.RateDiscountPolicy@50de186c
            key = fixDiscountPolicy value = hello.core.discount.FixDiscountPolicy@3f57bcad
             */
        }
    }

    // DiscountPolicy 타입이 두 개 존재하는 클래스
    @Configuration
    static class TestConfig {

        // 정률 할인
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        // 정액 할인
        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
