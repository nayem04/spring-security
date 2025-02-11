package com.nayem.databaseauth.common.configurations;

import com.nayem.databaseauth.common.routing.Router;
import com.nayem.databaseauth.domain.user.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class Security {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(Router.HELLO_ADMIN).hasRole("ADMIN")
                                .requestMatchers("/api/v1/roles/**").hasRole("ADMIN")
                                .requestMatchers(Router.HELLO_USER).hasAnyRole("ADMIN", "USER")
                                .requestMatchers("/api/v1/users/**").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(Router.HOME).permitAll()
                                .anyRequest().authenticated())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserService userService, PasswordEncoder passwordEncoder) {
        return new ProviderManager(
                List.of(new DaoAuthenticationProvider() {{
                    setUserDetailsService(userService);
                    setPasswordEncoder(passwordEncoder);
                }})
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
