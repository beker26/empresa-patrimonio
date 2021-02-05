package br.com.patrimonioempresa.apiescolapatrimonioempresa.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.patrimonioempresa.apiescolapatrimonioempresa.user.repository.UserRepository;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.user.service.AutenticacaoService;
import br.com.patrimonioempresa.apiescolapatrimonioempresa.user.service.TokenService;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/escola").permitAll()
				.antMatchers(HttpMethod.POST, "/v1/auth").permitAll()
				.antMatchers(HttpMethod.POST, "/v1/marca/**").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.POST, "/v1/marca/{marcaId}/patrimonio/**").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.PUT, "/v1/marca/{marcaId}/patrimonio/*").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.PUT, "/v1/marca/*").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.DELETE, "/v1/marca/*").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.DELETE, "/v1/marca/{marcaId}/patrimonio/*").hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().headers().frameOptions().sameOrigin()
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}

//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("123456"));
//	}

}
