package ecc.sogonsogon.BE.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/regiOn").permitAll()
                .requestMatchers("/user/signup").permitAll()
                .requestMatchers("/user/signin").permitAll()
                .requestMatchers("/nickname/{id}").permitAll()
                .requestMatchers("/main/top3").permitAll()
                .requestMatchers("/main/best3").permitAll()
                .requestMatchers("/main/show").permitAll());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}