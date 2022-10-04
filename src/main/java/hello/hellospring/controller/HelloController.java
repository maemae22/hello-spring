package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // http://localhost:8080/hello 에서 뒤에 hello랑 이거랑 매핑
    public String hello(Model model) {
        model.addAttribute("data", "data=spring!!");
        return "hello"; // hello.html 로 이동 (뷰 리졸버가 찾아줌)
    }

    // 2. MVC와 템플릿 엔진
    @GetMapping("hello-mvc")          // 파라미터 정보를 알고싶을 때는 Ctrl+P
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";  // Ctrl+클릭 하면 넘어감
    }

    // 3. API 방식 (1) - 문자
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " +name ;  // html이 아니고, 데이터를 그대로 그냥 내려줌(문자)
    }

    // 3. API 방식 (2) - 데이터 내리기
    // {"name":"maemae!!!!!!!!1"}  : JSON 방식으로 내려옴
    @GetMapping("hello-api")
    @ResponseBody  // 스프링 : 객체를 반환 + @ResponseBody => 기본으로 JSON으로 반환시켜줌 (default)
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();  // 치다가 Ctrl+Shift+Enter 치면 IntelliJ가 끝까지 자동완성 해줌 ㅎㅎ
        hello.setName(name); // Hello 의 name에 파라미터로 넘어온 name을 넘겨줌
        return hello; // 객체 넘김
    }

    static class Hello {
        private String name ;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
