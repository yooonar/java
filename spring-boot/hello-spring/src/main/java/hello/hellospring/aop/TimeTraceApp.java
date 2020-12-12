package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// @Aspect : AOP 추가 어노테이션
@Aspect
@Component // 컴포넌트 스캔함
public class TimeTraceApp {

    /*
        AOP 등록 방법
        1. @Component 어노테이션을 추가하여 컴포넌트 스캔

            @Around(execution([수식어] 리턴타입 [클래스이름].이름(파라미터)) 어노테이션을 작성해줌
            수식어: public, private, ... (생략가능)
            리턴타입: 리턴 타입
            클래스 이름: 클래스 이름(풀 패키지명으로 작성하며 생략 가능함)
            이름: 메소드명
            파라미터: 메소드의 파라미터
            *: 모든 값
            ..: 0개 이상

            @Around("execution(* hello.hellospring..*(..))") = hello.hellospring 하위 패키지는 다 적용해라.
        2. @Bean(SpringConfig.class) 으로 등록하여 쓰는 것을 선호함
            [@Bean 에 등록하는 소스]
            @Bean
            public TimeTraceApp timeTraceApp() {
                return new TimeTraceApp();
            }
     */
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        // System.currentTimeMillis() : 밀리세컨드 단위 시간
        // 시작 시간
        long start = System.currentTimeMillis();
        // joinPoint.toString() : 어떤 메소드를 호출하는지 알 수 있음
        System.out.println("START : " + joinPoint.toString());

        try {
            /*
                inline 변경 전(원본 소스)
                Object result = joinPoint.proceed();
                return result;
             */
            // joinPoint.proceed() : 다음 메소드로 진행
            return joinPoint.proceed();
        } finally {
            // 종료 시간
            long finish = System.currentTimeMillis();

            // 총 소요 시간
            long timeMs = finish - start;

            System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
