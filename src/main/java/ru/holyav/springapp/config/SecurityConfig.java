package ru.holyav.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.holyav.springapp.service.UserDetailsServiceImpl;

import static org.springframework.security.core.userdetails.User.withUserDetails;

@Configuration
@ComponentScan(basePackages = "ru.holyav.springapp")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;



   /*@Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          UserBuilder users = User.withDefaultPasswordEncoder();

           auth.inMemoryAuthentication()
                   .withUser(users.username("Admin").password("123").roles("ADMIN"))
                   .withUser(users.username("Andrey").password("123").roles("STUDENT"));

       }*/

    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/formForUpdate**", "/delete**")
                .hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .defaultSuccessUrl("/access-accepted",true)
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");



    }
}

