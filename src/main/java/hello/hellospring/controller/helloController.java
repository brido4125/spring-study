package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    //Using Static Content
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","spring!");
        return "hello";
    }

    //Using MVC and Template Engine
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);

        return "hello-template";
    }

    //Using API ver1 -> 메서드에서 String 반환
    @GetMapping("hello-string")
    @ResponseBody// 문자열 반환
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    //Using API ver2 - Using JSON -> 메서드에서 객체가 반환
    @GetMapping("hello-api")
    @ResponseBody // 객체 반환
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;


        //Getter and Setter -> java bean 표준
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
