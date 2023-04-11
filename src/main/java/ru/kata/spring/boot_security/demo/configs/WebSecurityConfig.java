package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UtilService;

import javax.annotation.PostConstruct;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserService userService;
    private final UtilService utilService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserService userService, UtilService utilService) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.utilService = utilService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                        .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .usernameParameter("email")
                    .successHandler(successUserHandler)
                    .permitAll()
                .and()
                    .logout().logoutSuccessUrl("/login").invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @PostConstruct
    public void addUsersWithRoles() {
        utilService.generateStartRoles();
        utilService.generateStartUsers();
    }
}