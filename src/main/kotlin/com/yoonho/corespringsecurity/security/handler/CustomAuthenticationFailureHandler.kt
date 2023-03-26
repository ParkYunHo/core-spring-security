package com.yoonho.corespringsecurity.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

/**
 * @author yoonho
 * @since 2023.03.26
 */
@Component
class CustomAuthenticationFailureHandler: SimpleUrlAuthenticationFailureHandler() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun onAuthenticationFailure(request: HttpServletRequest?, response: HttpServletResponse?, exception: AuthenticationException?) {
        val errorMsg = when(exception) {
            is BadCredentialsException -> "Invalid Username or Password"
            is InsufficientAuthenticationException -> "Invalid Secret Key"
            else -> "Invalid Username or Password"
        }

        val url = UriComponentsBuilder.newInstance()
                    .path("/login")
                    .queryParam("error", "true")
                    .queryParam("exception", exception?.let { errorMsg } ?: "")
                    .build().toUriString()
        setDefaultFailureUrl(url)

        super.onAuthenticationFailure(request, response, exception)
    }
}