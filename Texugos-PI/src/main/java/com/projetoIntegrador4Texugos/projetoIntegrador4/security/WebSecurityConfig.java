
package com.projetoIntegrador4Texugos.projetoIntegrador4.security;

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

		http.authorizeRequests().antMatchers("/home", "/", "/login", "/assets/**", "/assets/img/**" , "/assets/css/**").permitAll()
		.antMatchers("/login", "/admin/**").hasAnyRole("ADMINISTRADOR","ESTOQUISTA")
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
								)
				.formLogin(form -> form.loginPage("/admin/login")
						.defaultSuccessUrl("/admin", true).permitAll()
						.failureHandler(authFailHandler)
						).logout(logout -> logout.logoutSuccessUrl("/logout")
								.logoutSuccessUrl("/admin/login")
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
		
		
		/*auth.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(encoder);*/
		
	}

}
