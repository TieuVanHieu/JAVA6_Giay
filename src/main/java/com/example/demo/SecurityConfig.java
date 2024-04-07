package com.example.demo;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.AccountEntity;
import com.example.demo.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    AccountService accountService;
    @Autowired
    BCryptPasswordEncoder pe;

    // cung cap nguon du lieu dang nhap
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(username -> {
            try {
                AccountEntity user = accountService.findById(username).get();
                String password = pe.encode(user.getPassWord());
                String[] roles = user.getAuthorities().stream()
                    .map(er -> er.getRole().getRoleId())
                    .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (Exception e) {
                throw new UsernameNotFoundException(username +" not found");
            }
        });
    } 

    // phan quyen su dung
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/order/**").authenticated()
            .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
            .antMatchers("/rest/authorities").hasRole("DIRE")
            .anyRequest().permitAll();

        http.formLogin()
            .loginPage("/security/login/form")
            .loginProcessingUrl("/security/login")
            .defaultSuccessUrl("/security/login/success",false)
            .failureUrl("/security/login/error");

        http.rememberMe()
            .tokenValiditySeconds(86400);

        http.exceptionHandling()
            .accessDeniedPage("/security/unauthoried");

        http.logout()
            .logoutUrl("/security/logoff")
            .logoutSuccessUrl("/security/logoff/success");

        http.oauth2Login()
            .loginPage("/security/login/form")
            .defaultSuccessUrl("/oauth2/login/success",true)
            .failureUrl("/security/login/error")
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization");
    }

    // co che ma hoa mat khau
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // cho phep truy van rest api tu benh ngoai(domain khac)
    public void configure(WebSecurity web)throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

    
}
