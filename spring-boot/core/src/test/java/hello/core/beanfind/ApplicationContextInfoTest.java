package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 스프링 컨테이너에 정의된 스프링 빈 출력 테스트
public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 등록된 스프링 빈 출력하기
    // jUnit5 부터는 public 생략 가능
    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 정의된 빈 이름 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
            /*
            name = org.springframework.context.annotation.internalConfigurationAnnotationProcessor object = org.springframework.context.annotation.ConfigurationClassPostProcessor@934b6cb
            name = org.springframework.context.annotation.internalAutowiredAnnotationProcessor object = org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@55cf0d14
            name = org.springframework.context.annotation.internalCommonAnnotationProcessor object = org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@3b74ac8
            name = org.springframework.context.event.internalEventListenerProcessor object = org.springframework.context.event.EventListenerMethodProcessor@27adc16e
            name = org.springframework.context.event.internalEventListenerFactory object = org.springframework.context.event.DefaultEventListenerFactory@b83a9be
            name = appConfig object = hello.core.AppConfig$$EnhancerBySpringCGLIB$$9e8b65b4@2609b277
            name = memberService object = hello.core.member.MemberServiceImpl@1fd14d74
            name = getMemberRepository object = hello.core.member.MemoryMemberRepository@563e4951
            name = orderService object = hello.core.order.OrderServiceImpl@4066c471
            name = discountPolicy object = hello.core.discount.RateDiscountPolicy@2b175c00
             */
        }
    }

    @Test
    @DisplayName("스프링 내부에서 사용하는 빈만 출력하기")
    void findInfraStructureBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // getBeanDefinition : 각각의 빈에 대한 메타데이터 정보
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION : 내가 직접 등록한 애플리케이션 빈(외부 라이브러리 등)
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
                /*
                name = org.springframework.context.annotation.internalConfigurationAnnotationProcessor object = org.springframework.context.annotation.ConfigurationClassPostProcessor@55cf0d14
                name = org.springframework.context.annotation.internalAutowiredAnnotationProcessor object = org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@3b74ac8
                name = org.springframework.context.annotation.internalCommonAnnotationProcessor object = org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@27adc16e
                name = org.springframework.context.event.internalEventListenerProcessor object = org.springframework.context.event.EventListenerMethodProcessor@b83a9be
                name = org.springframework.context.event.internalEventListenerFactory object = org.springframework.context.event.DefaultEventListenerFactory@2609b277
                 */
            }
        }
    }

    @Test
    @DisplayName("애플리케이션 빈만 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // getBeanDefinition : 각각의 빈에 대한 메타데이터 정보
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // Role ROLE_APPLICATION : 내가 직접 등록한 애플리케이션 빈(외부 라이브러리 등)
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
                /*
                name = appConfig object = hello.core.AppConfig$$EnhancerBySpringCGLIB$$9e8b65b4@404bbcbd
                name = memberService object = hello.core.member.MemberServiceImpl@1e81f160
                name = getMemberRepository object = hello.core.member.MemoryMemberRepository@1acaf3d
                name = orderService object = hello.core.order.OrderServiceImpl@6986852
                name = discountPolicy object = hello.core.discount.RateDiscountPolicy@1bab8268
                 */
            }
        }
    }
}
