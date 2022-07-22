package hello.thymleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @GetMapping("/fragment")
    public String template(Model model) {
        model.addAttribute("name", "brido");
        model.addAttribute("age", "25");
        return "template/fragment/fragmentMain";
    }
}
