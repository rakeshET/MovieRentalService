package com.rakesh.handson.project.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth ->auth.anyRequest().authenticated()
        );
        http.httpBasic(withDefaults());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
