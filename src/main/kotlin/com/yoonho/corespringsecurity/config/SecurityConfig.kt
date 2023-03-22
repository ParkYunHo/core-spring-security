package com.yoonho.corespringsecurity.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

/**
 * @author yoonho
 * @since 2023.03.21
 */
@EnableWebSecurity
@Configuration
class SecurityConfig {

    /**
     * Custom PasswordEncoder 구현
     *
     * @author yoonho
     * @since 2023.03.21
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()

    /**
     * 사용자 계정 설정
     *
     * @author yoonho
     * @since 2023.03.21
     */
    @Bean
    fun userDetailsService(): InMemoryUserDetailsManager {
        val password = passwordEncoder().encode("1111")

        val user = User
            .withUsername("user")
            .password(password)
            .roles("USER")
            .build()

        val manager = User
            .withUsername("manager")
            .password(password)
            .roles("MANAGER", "USER")
            .build()

        val admin = User
            .withUsername("admin")
            .password(password)
            .roles("ADMIN", "USER", "MANAGER")
            .build()

        return InMemoryUserDetailsManager(user, manager, admin)
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer { it.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()) }


    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {

        http
            .authorizeHttpRequests()
            .requestMatchers("/").permitAll()
            .requestMatchers("/mypage").hasRole("USER")
            .requestMatchers("/messages").hasRole("MANAGER")
            .requestMatchers("/config").hasRole("ADMIN")
            .anyRequest().authenticated()

        http
            .formLogin()

        return http.build()
    }

}