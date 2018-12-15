package pl.sienkiewicz.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan({"pl.sienkiewicz.controllers", "pl.sienkiewicz.services"})
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer{

}
