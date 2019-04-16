package fr.afcepf.al33;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication()
	  .withUser("user1").password(passwordEncoder.encode("pwd1")).roles("USER").and()
	  .withUser("admin1").password(passwordEncoder.encode("pwd1")).roles("ADMIN").and()
	  .withUser("user2").password(passwordEncoder.encode("pwd2")).roles("USER").and()
	  .withUser("admin2").password(passwordEncoder.encode("pwd2")).roles("ADMIN");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	http.authorizeRequests()
		.antMatchers("/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js").permitAll()
    	.antMatchers("/rest/**").permitAll()
    	.antMatchers("/mvc/**").permitAll()
		//.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().cors() //enable CORS (avec @CrossOrigin sur class @RestController)
		.and().csrf().disable();
		
		//.and().httpBasic() 
		
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
