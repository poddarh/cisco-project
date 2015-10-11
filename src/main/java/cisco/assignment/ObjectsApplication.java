package cisco.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="cisco.assignment")
public class ObjectsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectsApplication.class, args);
    }
    
}
