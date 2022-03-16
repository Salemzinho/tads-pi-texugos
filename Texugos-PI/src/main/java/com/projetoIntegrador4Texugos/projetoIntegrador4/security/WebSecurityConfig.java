package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioUserDetailService usuarioDetailService;
	
	@Autowired
	private AuthProviderService authProvider;
	
	@Autowired
	private CustomAuthenticationFailureHandler authFailHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login", "/assets/**")
		.permitAll()
		.antMatchers("/usuario", "/usuario/**").hasRole("ADMINISTRADOR")
		.anyRequest().authenticated()
				.and()
				.exceptionHandling()
					.accessDeniedPage("/home?error=true")
					.and()
				.formLogin(form -> form.loginPage("/login")
						.defaultSuccessUrl("/home", true).permitAll()
						.failureHandler(authFailHandler)
						).logout(logout -> logout.logoutSuccessUrl("/logout")
								.logoutSuccessUrl("/login")
								).csrf().disable();
		
		//Adicionar as permissoes por role e url
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth
		.authenticationProvider(authProvider)
        .userDetailsService(usuarioDetailService)
        .passwordEncoder(encoder);
		
		
		/*auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(encoder);*/
		
	}

}
