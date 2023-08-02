package com.vendorinfluencer.configuration;

import com.vendorinfluencer.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class ProjectSecurityConfig{

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public UserDetailsService userDetailsService(){
        return new VendorDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and().authenticationProvider(authenticationProvider())
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .cors().configurationSource(request -> {
                   CorsConfiguration config = new CorsConfiguration();
                   config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));//Enter the path of frontend port
                   config.setAllowedMethods(Collections.singletonList("*"));
                   config.setAllowCredentials(true);
                   config.setAllowedHeaders(Collections.singletonList("*"));
                   config.setMaxAge(3600L);
                   return config;
               }).and().csrf().disable()
               .authorizeHttpRequests()
                .requestMatchers("/blockVendor","/unblockVendor","/getAllVendors"
                        ,"/addProduct","/deleteProduct",
                        "/showAllProducts","/influencerByCategory","/updateVendor","/addInfluencerToList","/getAddedInfluencers").authenticated()
                .requestMatchers( "/register","/authenticate").permitAll().and()
               .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}