package br.com.patrimonioempresa.apiescolapatrimonioempresa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.user.model.User;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket getSwaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.patrimonioempresa.apiescolapatrimonioempresa"))
				.paths(PathSelectors.ant("/**")).build()
				.ignoredParameterTypes(User.class);
	}

}
