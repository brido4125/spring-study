package hello.servlet2.web.frontcontroller.v2;

import hello.servlet2.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    //기존 v1 :return void , v2 => return MyView 객체(view renderingg하는 객체를 따로 생성해서 공통부분 묶기)
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
