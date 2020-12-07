package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        // model.addAttribute(key, value);
        // ${data} 값으로 치환됨 <- <p th:text="'안녕하세요.' + ${data}">안녕하세요. 손님</p>
        model.addAttribute("data", "hello!");

        // /src/main/resources/templates/hello.html 파일을 찾아서 연결해라.
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body 에 해당 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {

        // String name = spring 으로 입력한다면
        // 파일명이 아닌 문자열 "hello spring" 으로 될 것임
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // json 반환 디폴트 세팅
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        // json 형식 : {"name":"spring"}
        return hello; // 객체 넘기기
    }


    // HelloController.Hello 클래스로 사용 가능함
    static class Hello {
        private String name;

        // property 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
