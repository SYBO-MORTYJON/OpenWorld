package com.napa.OpenWorld.security;


import com.napa.OpenWorld.service.UserServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserServise userServise;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
     httpSecurity
                .authorizeRequests()
                    .antMatchers("/registration").not().fullyAuthenticated()
                    .antMatchers("/", "/webjars/**", "/css/**", "/js/**","/photos/**","/images/**","/zip/**").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf()
                    .disable()
                    .logout()
                    .permitAll()
                .logoutSuccessUrl("/logiin")
                .and()
                .csrf()
                .disable()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
     .and()
      ;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder aut) throws Exception{
        aut.userDetailsService(userServise).passwordEncoder(passwordEncoder());
    }
}