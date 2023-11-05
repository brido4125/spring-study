package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//Springboot의 내장된 Tomcat이 HttpServlet을 구현 (DefaultServlet 클래스)
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //"/hello" Path로 들어올 경우, 아래의 servie 로직이 실행된다.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        System.out.println("HelloServlet.service");

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");//query parameter 에 한글
        response.getWriter().write("hello " + username); // HTTP Response Body에 적을 String
    }
}
