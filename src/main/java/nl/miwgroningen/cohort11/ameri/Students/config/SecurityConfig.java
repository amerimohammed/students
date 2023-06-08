package nl.miwgroningen.cohort11.ameri.Students.config;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.cohort11.ameri.Students.model.RoleType;
import nl.miwgroningen.cohort11.ameri.Students.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Mohammed Alameri on 07/06/2023.
 * handles access restrictions to the application pages
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests((authorize) -> authorize
                .antMatchers("/css/**", "/webjars/**").permitAll()
                .antMatchers("/", "/cohort").permitAll()
                .antMatchers(HttpMethod.POST,"/student").hasAuthority(RoleType.ADMIN.toString())
                .anyRequest().authenticated()
        ).formLogin()
                .and().logout().logoutSuccessUrl("/");

        return httpSecurity.build();
    }
}