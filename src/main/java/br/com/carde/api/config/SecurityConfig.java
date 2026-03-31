package br.com.carde.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RateLimitFilter rateLimitFilter;
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(RateLimitFilter rateLimitFilter, JwtAuthFilter jwtAuthFilter) {
        this.rateLimitFilter = rateLimitFilter;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/v1/admin/auth/login").permitAll()
                .requestMatchers("/api/v1/admin/**").authenticated()
                .requestMatchers("/api/v1/**").permitAll()
                .anyRequest().denyAll())
            .headers(headers -> headers
                .httpStrictTransportSecurity(hsts -> hsts
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31_536_000))
                .contentTypeOptions(Customizer.withDefaults())
                .frameOptions(frame -> frame.deny()))
            .addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtFilterRegistration(JwtAuthFilter filter) {
        FilterRegistrationBean<JwtAuthFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Configuração para rotas admin — permite métodos de escrita e header Authorization
        CorsConfiguration adminConfig = new CorsConfiguration();
        adminConfig.setAllowedOriginPatterns(List.of(
                "https://carde.com.br",
                "https://admin.carde.com.br",
                "https://carde-admin*.vercel.app",  // cobre produção e previews
                "http://localhost:5173"
        ));
        adminConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        adminConfig.setAllowedHeaders(List.of("Content-Type", "Accept", "Accept-Language", "Authorization"));
        adminConfig.setMaxAge(3600L);

        // Configuração para rotas públicas — apenas leitura
        CorsConfiguration publicConfig = new CorsConfiguration();
        publicConfig.setAllowedOrigins(List.of(
                "https://carde.com.br",
                "https://admin.carde.com.br"
        ));
        publicConfig.setAllowedMethods(List.of("GET", "OPTIONS"));
        publicConfig.setAllowedHeaders(List.of("Content-Type", "Accept", "Accept-Language"));
        publicConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/admin/**", adminConfig);
        source.registerCorsConfiguration("/api/**", publicConfig);
        return source;
    }
}
