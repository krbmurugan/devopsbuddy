package com.devopsbuddy.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.devopsbuddy.backend.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	private Environment environ;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	 

	
	private static final String[] PUBLIC_MATCHERS= {
			"/templates/**",
			"/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/h2console/**"
	};

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		List activeProfiles = Arrays.asList(environ.getActiveProfiles());
		
		if(activeProfiles.contains("dev")) {
			log.info("Configuring for dev profile");
			http.csrf().disable();
			http.headers().frameOptions().disable();
		}
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
//		auth
//			.inMemoryAuthentication()
//		
//			.withUser("user").password("{noop}pwd")
//			.roles("USER");
		
		//the {noop} command for password encoder is added in the user.java class in setPassword().
		auth.userDetailsService(userSecurityService);
			
		
	}
	
 
	
}

