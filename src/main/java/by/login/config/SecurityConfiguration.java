package by.login.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**");
//        web.ignoring().antMatchers("/static/css/**");
//        web.ignoring().antMatchers("/static/js/**");
//        web.ignoring().antMatchers("/static/pic/**");
//        web.ignoring().antMatchers("/webjars/**");
//        web.ignoring().antMatchers("/bootstrap/**");
//        web.ignoring().antMatchers("/3.3.7-1/**");
//        web.ignoring().antMatchers("/css/**");
//        web.ignoring().antMatchers("/js/**");
//    }
}
