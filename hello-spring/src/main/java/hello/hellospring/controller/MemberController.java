package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 스프링이 @Controller 어노테이션을 보고 처음 실행할 때 MemberController 객체를 생성해서 대기함
// 이것을 스프링 컨테이너에서 스프링 빈이 관리된다고 한다.
@Controller
public class MemberController {
    private final MemberService memberService;

    // @Autowired: 스프링 컨테이너에서 MemberService를 자동으로 가져온다.
    // 컨트롤러와 서비스를 연결시켜줌 = 의존성 주입(Dependency Injection)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm"; // templates/members/createMemberForm.html
    }
}
