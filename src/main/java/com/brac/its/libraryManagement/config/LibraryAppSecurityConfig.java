package com.brac.its.libraryManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class LibraryAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("user")
                .password("{noop}pass")
                .roles("USER");
    }


    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup", "/doSignup").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll().loginProcessingUrl("/doLogin")
                .and()
                .logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
                //.logout().permitAll().logoutUrl("/logout");
    }

    //m-2 lesson 4 done => custom login page
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll();
    }*/

    // module-2, lesson-3 => authorize specific url, here /delete needs admin authorization
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/delete/**")
                .hasAnyAuthority("ADMIN", "ADMIN2")
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }*/
}
