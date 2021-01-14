package nl.uva.sne.drip;

import java.net.URI;
import nl.uva.sne.drip.dao.ToscaTemplateDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.function.HandlerFunction;
import static org.springframework.web.servlet.function.RequestPredicates.GET;
import org.springframework.web.servlet.function.RouterFunction;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@PropertySources({
    @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
})
@EnableMongoRepositories(basePackageClasses = {ToscaTemplateDAO.class})
@ComponentScan(basePackages = {"nl.uva.sne.drip", "nl.uva.sne.drip.api",
    "nl.uva.sne.drip.configuration", "nl.uva.sne.drip.dao", "nl.uva.sne.drip.service"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }



    class ExitException extends RuntimeException implements ExitCodeGenerator {

        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
