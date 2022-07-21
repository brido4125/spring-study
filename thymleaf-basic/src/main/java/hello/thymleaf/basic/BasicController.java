package hello.thymleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "<b>Hello Spring</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("brido", 25);
        User userB = new User("daebak", 12);

        List<User> lists = new ArrayList<>();
        lists.add(userA);
        lists.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put(userA.getUsername(), userA);
        map.put(userB.getUsername(), userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", lists);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    @GetMapping("/basic-objects")
    public String basicObject(HttpSession httpSession) {
        httpSession.setAttribute("sessionData", "Hello session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello" + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");

        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "spring");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    private void addUsers(Model model) {
        List<User> users = new ArrayList<>();
        users.add(new User("brido", 25));
        users.add(new User("daebak", 12));
        users.add(new User("yebin", 22));

        model.addAttribute("users", users);
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "spring");
        return "basic/comments";
    }
}
