package com.algaworks.socialbooks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("spring")
                .password(encoder.encode("secret"))
                .roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and().csrf().disable();

        http.headers().frameOptions().disable();
    }
}
