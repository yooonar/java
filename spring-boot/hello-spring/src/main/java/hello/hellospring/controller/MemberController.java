package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 스프링 컨테이너가 뜰 때 생성됨. 스프링 컨테이너가 가지고 있음
@Controller
public class MemberController {

    /*
        MemberService 는 특별한 기능이 없기 때문에 인스턴스를 생성하기 보다는
        private final MemberService memberService = new MemberService();

        다른 컨트롤러와 공용으로 같이 사용할 수 있도록 해주는 것이 좋다.
     */
    private final MemberService memberService;

    // @Autowired : 컨트롤러에서 서비스를 연결할 때 사용한다.
    // 컨트롤러 생성자에 Autowired 를 쓰는 경우 MemberController 가 생성될 때 스프링이 스프링 빈(컨테이너)에 있는 memberService 객체를 가져와 연결시켜준다.
    // 이것을 Dependency Injection 의존성 주입 이라고 한다. (밖에 있는 데이터를 스프링이 넣어줌)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // post 방식으로 form 데이터를 전송받는 곳
    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member.getName() = " + member.getName());
        // 회원 가입
        memberService.join(member);

        // 회원가입 완료 후 메인으로 이동
        return "redirect:/";
    }
}
