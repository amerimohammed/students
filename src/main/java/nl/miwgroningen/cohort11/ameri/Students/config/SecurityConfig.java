package nl.miwgroningen.cohort11.ameri.Students.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Mohammed Alameri on 07/06/2023.
 * @project handles access restrictions to the application pages
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests((authorize) -> authorize
                .antMatchers("/css/**", "/webjars/**").permitAll()
                .antMatchers("/", "/cohort").permitAll()
                .anyRequest().authenticated()
        ).formLogin().and().logout().logoutSuccessUrl("/");

        return httpSecurity.build();
    }
}
