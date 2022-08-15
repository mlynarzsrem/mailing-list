package com.jmlynarz.mailinglist.config

import com.jmlynarz.mailinglist.auth.JsonObjectAuthenticationFilter
import com.jmlynarz.mailinglist.auth.RestAuthenticationFailureHandler
import com.jmlynarz.mailinglist.auth.RestAuthenticationSuccessHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
        val restAuthenticationFailureHandler: RestAuthenticationFailureHandler,
        val restAuthenticationSuccessHandler: RestAuthenticationSuccessHandler) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun authenticationFilter(authenticationManager: AuthenticationManager): JsonObjectAuthenticationFilter {
        val filter = JsonObjectAuthenticationFilter()
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler)
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler)
        filter.setAuthenticationManager(authenticationManager)
        return filter
    }

    @Bean
    fun filterChain(http: HttpSecurity, authenticationFilter: JsonObjectAuthenticationFilter) : SecurityFilterChain {
        return http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()
                .formLogin().and()
                .exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                .build()
    }
}