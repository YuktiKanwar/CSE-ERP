package spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.inMemoryAuthentication()
		.withUser("root")
		.password("root")
		.authorities("ROLE_SUPER");
		authenticationMgr.userDetailsService(userDetailsService);
//		authenticationMgr.inMemoryAuthentication()
//			.withUser("yukti")
//			.password("lagnesh")
//			.authorities("ROLE_ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/homePage").permitAll()
			.and()
				.formLogin().loginPage("/")
				.defaultSuccessUrl("/homePage")
				.failureUrl("/?error")
				.usernameParameter("username").passwordParameter("password")				
			.and()
				.logout().logoutSuccessUrl("/?logout")
			.and()
			.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/Accounts/*").access("hasRole('ROLE_ACCOUNTS')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/Admission/*").access("hasRole('ROLE_ADMISSION')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
	
		http.authorizeRequests()
		.antMatchers("/Faculty/*").access("hasAnyRole('ROLE_FACULTY','ROLE_HOD')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/HOD/*").access("hasRole('ROLE_HOD')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/Library/*").access("hasRole('ROLE_LIBRARY')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/T&P/*").access("hasRole('ROLE_T&P')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/Transport/*").access("hasRole('ROLE_TRANSPORT')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
		http.authorizeRequests()
		.antMatchers("/User/*").access("hasRole('ROLE_SUPER')")
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}