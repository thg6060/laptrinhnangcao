package inventory.sercurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	DataSource dataSource;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			    .antMatchers("/resources/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")	
				.defaultSuccessUrl("/index",true)
				.permitAll()
				.and()
			    .csrf().disable()
			.logout()
				.permitAll();
	}
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select user_name as username,password as password,active_flag as enabled from users where user_name = ? and active_flag = 1")
        .authoritiesByUsernameQuery("select user_name as username,r.role_name as authority from users as u " + 
        		"inner join user_role as ur " + 
        		"on u.id = ur.user_id " + 
        		"inner join role as r " + 
        		"on r.id = ur.role_id " + 
        		"where u.user_name = ? ")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
 
        
//            .inMemoryAuthentication()
//                .withUser("user").password("{noop}123").roles("USER");
    }
}
