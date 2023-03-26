package com.yoonho.corespringsecurity.config

import com.yoonho.corespringsecurity.security.details.FormAuthenticationDetailsSource
import com.yoonho.corespringsecurity.security.handler.CustomAccessDeniedHandler
import com.yoonho.corespringsecurity.security.handler.CustomAuthenticationFailureHandler
import com.yoonho.corespringsecurity.security.handler.CustomAuthenticationSuccessHandler
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter

/**
 * @author yoonho
 * @since 2023.03.21
 */
@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val authenticationDetailsSource: FormAuthenticationDetailsSource,
    private val customAuthenticationSuccessHandler: CustomAuthenticationSuccessHandler,
    private val customAuthenticationFailureHandler: CustomAuthenticationFailureHandler,
    private val customAccessDeniedHandler: CustomAccessDeniedHandler
) {
    private val log = LoggerFactory.getLogger(this::class.java)

//    /**
//     * 사용자 계정 설정 (수동설정)
//     *
//     * @author yoonho
//     * @since 2023.03.21
//     */
//    @Bean
//    fun userDetailsService(): InMemoryUserDetailsManager {
//        val password = passwordEncoder().encode("1111")
//
//        val user = User
//            .withUsername("user")
//            .password(password)
//            .roles("USER")
//            .build()
//
//        val manager = User
//            .withUsername("manager")
//            .password(password)
//            .roles("MANAGER", "USER")
//            .build()
//
//        val admin = User
//            .withUsername("admin")
//            .password(password)
//            .roles("ADMIN", "USER", "MANAGER")
//            .build()
//
//        return InMemoryUserDetailsManager(user, manager, admin)
//    }

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
     * WebSecurity 설정
     * <p>
     *     - static resource에 대한 인증 Bypass 처리
     *
     * @author yoonho
     * @since 2023.03.26
     */
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer =
        WebSecurityCustomizer {
            it
                .ignoring()
                // static Resource 인증 Ignore
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                // h2 console 인증 Ignore
                .requestMatchers("/h2/**")
        }

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {

        http
            .authorizeHttpRequests()
            .requestMatchers("/", "/users", "user/login/**", "/login*").permitAll()
            .requestMatchers("/h2/**").permitAll()
            .requestMatchers("/mypage").hasRole("USER")
            .requestMatchers("/messages").hasRole("MANAGER")
            .requestMatchers("/config").hasRole("ADMIN")
            .anyRequest().authenticated()

        http
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login_proc")
            .defaultSuccessUrl("/")
            .authenticationDetailsSource(authenticationDetailsSource)
            .successHandler(customAuthenticationSuccessHandler)
            .failureHandler(customAuthenticationFailureHandler)
            .permitAll()

        http
            .exceptionHandling()
            .accessDeniedHandler(this.setAccessDeniedHandler())

        http
            .headers()
            .addHeaderWriter(XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))

        return http.build()
    }

    private fun setAccessDeniedHandler(): CustomAccessDeniedHandler {
        customAccessDeniedHandler.setErrorPage("/denied")
        return customAccessDeniedHandler
    }
}