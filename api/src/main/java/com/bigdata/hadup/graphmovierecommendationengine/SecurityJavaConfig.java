package com.bigdata.hadup.graphmovierecommendationengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("adam123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("marek123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("pawel123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("maciek123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("michal123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("radek123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("anna123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("maria123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("magda123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("karolina123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("karol123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("filip123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("marcin123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("jarzy123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("kuba123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("mateusz123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("bartek123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("dorota123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("damian123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("mariusz123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("kuba123").password(encoder().encode("qwerty")).roles("USER");
        auth.inMemoryAuthentication().withUser("artur123").password(encoder().encode("qwerty")).roles("USER");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .cors().and()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/movies/rate/**").authenticated()
                //.antMatchers("/api/admin/**").hasRole("USER")
                .and()
                .formLogin()
                .successHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                    response.setStatus(200);
                })
                .failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
                    response.setStatus(401);
                })
                .permitAll()
                .and()
                .logout().permitAll();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "credentials"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}