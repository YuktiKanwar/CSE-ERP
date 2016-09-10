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
		authenticationMgr.userDetailsService(userDetailsService);
//		authenticationMgr.inMemoryAuthentication()
//			.withUser("yukti")
//			.password("lagnesh")
//			.authorities("ROLE_ADMIN");
//		authenticationMgr.inMemoryAuthentication()
//		.withUser("lagnesh")
//		.password("yukti")
//		.authorities("ROLE_USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/homePage").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			.and()
				.formLogin().loginPage("/loginPage")
				.defaultSuccessUrl("/homePage")
				.failureUrl("/loginPage?error")
				.usernameParameter("username").passwordParameter("password")				
			.and()
				.logout().logoutSuccessUrl("/loginPage?logout"); 
		http.authorizeRequests()
		.antMatchers("/newPage").access("hasRole('ROLE_ADMIN')");
		
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}