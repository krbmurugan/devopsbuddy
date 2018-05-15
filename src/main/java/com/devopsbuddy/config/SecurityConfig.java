package com.devopsbuddy.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

	
	private static final String[] PUBLIC_MATCHERS= {
			
			"/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**/*",
	};

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/payload")
			.failureUrl("/login?error").permitAll()
			.and()
			.logout().permitAll();
		
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
		
			.withUser("user").password("{noop}pwd")
			.roles("USER");
			
		
	}
	
	@Bean
	public SpringTemplateEngine setTemplate() {
		log.info("setting template..");

		Set securityDialects = new HashSet<IDialect>();
		securityDialects.add(new SpringSecurityDialect());
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setAdditionalDialects(securityDialects);
		
		return engine;
	}
	
	
	
	
	
}

