package tom.web.api.imgurwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations="applicationContext.xml")
public class Application {


    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
    
}
