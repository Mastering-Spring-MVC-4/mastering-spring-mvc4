package masterSpringMvc;

import masterSpringMvc.config.PictureUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({PictureUploadProperties.class})
public class MasterSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSpringMvcApplication.class, args);
    }
}
