package com.ott.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
@ComponentScan(basePackages = "com.ott")
@Configuration
public class SecurityConfiguration  {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;

   //HttpSecurity security - 사용자 인증 정보를 가지고 있는 객체
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
	    security.authorizeRequests()
	    	.antMatchers("/").permitAll()
	        .antMatchers("/mypage/**").authenticated()
	        .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
	        .antMatchers("/adminPage/**").access("hasAuthority('ADMIN')")
	        .and()
	        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	        .and()
	        .formLogin()
	            .loginPage("/login")
	            .usernameParameter("id")
	            .loginProcessingUrl("/login")
	            .defaultSuccessUrl("/datecheck")
	            .and()
	        .sessionManagement().sessionFixation().migrateSession();
	    security.exceptionHandling().accessDeniedPage("/layout/admin/accessDenied");
	    security.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login")
	        .and()
	        .userDetailsService(securityUserDetailsService);
	    	
	    return security.build();
	}
	
   /*
    * 비밀번호 암호화
    */
   @Bean
   public PasswordEncoder passwordEncoder() {
	   
	   return PasswordEncoderFactories.createDelegatingPasswordEncoder();
   }
  
}