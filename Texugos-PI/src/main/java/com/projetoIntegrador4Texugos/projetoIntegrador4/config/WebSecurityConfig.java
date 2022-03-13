package com.projetoIntegrador4Texugos.projetoIntegrador4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/");*/
		
		http.authorizeRequests()
		.antMatchers("/", "/home/**","/assets/**").permitAll()
			.anyRequest().authenticated().and().formLogin().loginPage("/");
    }
	
	
}
