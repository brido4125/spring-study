package hello.servlet2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Servlet2Application {

	public static void main(String[] args) {
		SpringApplication.run(Servlet2Application.class, args);
	}

}
