package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    //add support for JDBC... no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        //define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )

                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()

                )
                .logout(logout ->
                        logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                        )
        ;
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails tan = User.builder()
//                .username("tan")
//                .password("{noop}tan123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails hieu = User.builder()
//                .username("hieu")
//                .password("{noop}hieu123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//        UserDetails vinh = User.builder()
//                .username("vinh")
//                .password("{noop}vinh123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(tan, hieu, vinh);
//    }
}