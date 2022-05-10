
package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {	
	
	@Configuration
	@Order(1)
	public static class CliConfigAdapter extends WebSecurityConfigurerAdapter{
		
		public CliConfigAdapter() {
			super();
		}
		
		@Autowired
		private ClienteUserDetailService clienteDetailService;
		
		@Autowired
		private AuthClienteProviderService authProvider;
		
		@Autowired
		private CustomAuthenticationFailureHandler authFailHandler;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http
			.csrf().disable().authorizeRequests()
			.antMatchers("/cliente/**").hasRole("CLIENTE")
			.antMatchers("/cliente/**").authenticated()
			.antMatchers("/home", "/", "/admin/login", "/assets/**", "/assets/img/**" , "/assets/css/**")
				.permitAll()
			.and()
			.exceptionHandling()
				.accessDeniedPage("/home?error=true")
				.and()
			.formLogin(form -> form.loginPage("/login")
					.defaultSuccessUrl("/home", true).permitAll()
					.failureHandler(authFailHandler)
					).logout(logout -> logout.logoutSuccessUrl("/logout-cliente")
							.logoutSuccessUrl("/login")
							.deleteCookies("JSESSIONID")
							);
		}		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			auth
			.authenticationProvider(authProvider)
	        .userDetailsService(clienteDetailService)
	        .passwordEncoder(encoder);
		}
	}
	
	@Configuration
	@Order(2)
	public static class FuncConfigAdapter extends WebSecurityConfigurerAdapter{
		
		public FuncConfigAdapter() {
			super();
		}
		
		@Autowired
		private UsuarioUserDetailService usuarioDetailService;
		
		@Autowired
		private AuthProviderService authProvider;
		
		@Autowired
		private CustomAuthenticationFailureHandler authFailHandler;
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http
			.csrf().disable().authorizeRequests()
			.antMatchers("/admin/login", "/admin/**", "/produto/**", "/usuario/**").hasAnyRole("ADMINISTRADOR","ESTOQUISTA")
			.antMatchers("/usuario", "/usuario/**").hasRole("ADMINISTRADOR")
			.antMatchers("/admin/login", "/admin/**", "/produto/**", "/usuario/**").authenticated()
			.antMatchers("/home", "/", "/admin/login", "/assets/**", "/assets/img/**" , "/assets/css/**")
			.permitAll()
					.and()
					.exceptionHandling()
						.accessDeniedPage("/home?error=true")
						.and()
						.formLogin(form -> form.loginPage("/admin/login")
								.defaultSuccessUrl("/admin", true).permitAll()
								.failureHandler(authFailHandler)
								).logout(logout -> logout.logoutSuccessUrl("/logout")
									.logoutSuccessUrl("/admin/login")
									.deleteCookies("JSESSIONID")
									)
					.csrf().disable();
		}		
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			auth
			.authenticationProvider(authProvider)
	        .userDetailsService(usuarioDetailService)
	        .passwordEncoder(encoder);	
		}
	}

}
