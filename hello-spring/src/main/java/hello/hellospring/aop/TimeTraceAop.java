package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP
@Component // 스프링빈에 등록(컴포넌트 스캔 방법)
public class TimeTraceAop {

    // execution(* 실행 패키지명.하위.클래스명 등 원하는 조건 추가)
    @Around("execution(* hello.hellospring..*(..))") // 어디에 적용할 것인지(패키지 하위는 모두 적용)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis(); // 시작 시간 가져오기
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // 다음 메소드 진행
        } finally {
            long finish = System.currentTimeMillis(); // 종료 시간 가져오기
            long timeMs = finish - start; // 시간차
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
