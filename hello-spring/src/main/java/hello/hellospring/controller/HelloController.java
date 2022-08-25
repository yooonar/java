package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*; // GetMapping, RequestParam, ResponseBody

@Controller
public class HelloController {

    // MVC 방식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; // resource/templates/hello.html 과 연결됨
    }

    // MVC 방식
    @GetMapping("hello-mvc") // @GetMapping("요청url") http://localhost:8080/hello-mvc?name=test
    public String helloMvc(@RequestParam(name = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template"; // reutrn "html파일명"; resource/templates/hello-template.html 과 연결됨
    }

    // API 방식
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring" 뷰페이지가 없고 이 문자가 페이지에 그대로 출력된다.
    }

    // API 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체 생성
        /*
        HelloController.Hello hello = new HelloController.Hello(); 가 아닌 Hello hello = new Hello(); 로 객체를 생성하는 이유
        외부 클래스에서는 내부 클래스 객체 생성이 바로 가능하기 때문이다.
        다른 패키지나 클래스에서는 그렇게 생성하는 것이 맞다.
         */
        hello.setName(name); // 파라미터로 넘어온 name을 저장함
        return hello; // 객체를 json 방식으로 리턴함
    }

    static class Hello { // HelloController.Hello 로 사용 가능함
        private String name;

        // java bean 프로퍼티 규약 (getter, setter) 직접적으로 name에 접근할 수 없음
        // 프로퍼티 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
