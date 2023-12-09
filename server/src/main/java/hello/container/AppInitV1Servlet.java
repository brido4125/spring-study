package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

//서블릿을 등록하기 위한 클래스
//서블릿을 등록해야 하니 ServletContext를 인자로 받아야하지만,
//목적이 다른 경우는 ServletContext에 대한 의존성을 제거 가능.
public class AppInitV1Servlet implements AppInit {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartup");

        //@WebServlet 방식이 아닌 자바 코드를 통한 서블릿 등록하는 방법
        ServletRegistration.Dynamic helloServlet =
            servletContext.addServlet("helloServlet", new HelloServlet());

        helloServlet.addMapping("/hello-servlet");
    }
}
