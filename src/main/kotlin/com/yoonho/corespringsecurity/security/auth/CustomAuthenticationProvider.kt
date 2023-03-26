package com.yoonho.corespringsecurity.security.auth

import com.yoonho.corespringsecurity.security.details.FormWebAuthenticationDetails
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * @author yoonho
 * @since 2023.03.23
 */
@Component
class CustomAuthenticationProvider(
    private val userDetailsService: CustomUserDetailsService,
    private val passwordEncoder: PasswordEncoder
): AuthenticationProvider {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {
        val username = authentication?.name
        val password = authentication?.credentials as String

        log.info(" >>> [authenticate] request user authentication - username: $username, password: $password")

        // username 검증
        val accountContext = userDetailsService.loadUserByUsername(username!!) as AccountContext

        // password 검증
        if(!passwordEncoder.matches(password, accountContext.password)) {
            throw BadCredentialsException("BadCredentialsException")
        }

        // Details의 secretKey(속성) 체크 (테스트용도)
        val details = authentication.details as FormWebAuthenticationDetails
        val secretKey = details.getSecretKey()
        if(secretKey == null || secretKey != "secret") {
            throw InsufficientAuthenticationException("InsufficientAuthenticationException")
        }

        return UsernamePasswordAuthenticationToken(accountContext.account(), null, accountContext.authorities)
    }

    override fun supports(authentication: Class<*>?): Boolean =
        UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}