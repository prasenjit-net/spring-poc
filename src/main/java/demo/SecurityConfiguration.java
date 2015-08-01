package demo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import demo.model.AuthenticatedUser;
import demo.model.User;
import demo.repo.UserRepository;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("css/**", "/webjars/**", "js/**", "/img/**")
				.and();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/auth/facebook").permitAll().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				.sessionFixation().changeSessionId().and().logout()
				.logoutSuccessUrl("/logout").permitAll()
				.logoutSuccessUrl("/home").invalidateHttpSession(true).and()
				.authorizeRequests().antMatchers("/signup", "/", "/home")
				.anonymous().anyRequest().fullyAuthenticated().and().csrf()
				.disable().apply(new SpringSocialConfigurer())
				.postLoginUrl("/home");
	}

	@Bean
	public SocialUserDetailsService socialUser(UserRepository userRepository) {
		return userId -> {
			User user = userRepository.findOne(userId);
			return new AuthenticatedUser(user.getEmail(), user.getFirstName());
		};
	}

}
