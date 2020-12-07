package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
