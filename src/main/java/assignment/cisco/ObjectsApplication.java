package assignment.cisco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan(basePackages="assignment.cisco")
public class ObjectsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectsApplication.class, args);
    }
    
    @Bean  // Magic entry 
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
    
}
