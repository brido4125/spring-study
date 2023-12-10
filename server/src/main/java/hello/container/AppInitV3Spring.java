package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV3Spring implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitV3Spring.onStartup");

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(HelloConfig.class);

        //스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);

        //Dispatcher servlet을 서블릿 컨테이너에 등록
        ServletRegistration.Dynamic dispatcherV3 = servletContext.addServlet("dispatcherV3", dispatcherServlet);

        // 모든요청들은 dispatcherV3를 거치도록 설정
        dispatcherV3.addMapping("/");
    }
}
