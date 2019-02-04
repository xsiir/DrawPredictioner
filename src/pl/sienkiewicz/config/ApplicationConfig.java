package pl.sienkiewicz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan({ "pl.sienkiewicz.controllers", "pl.sienkiewicz.services", "pl.sienkiewicz.mongodb"})
@EnableWebMvc
public class ApplicationConfig extends AbstractMongoConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/", ".jsp");
	}
	
	@Bean
	@Override
	public MongoClient mongoClient() {
		return new MongoClient("127.0.0.1");
	}

	@Override
	@Bean
	protected String getDatabaseName() {
		return "predictioner";
	}

}
