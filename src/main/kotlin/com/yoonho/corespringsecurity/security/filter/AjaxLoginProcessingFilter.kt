package com.yoonho.corespringsecurity.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.yoonho.corespringsecurity.api.user.dto.AccountInput
import com.yoonho.corespringsecurity.security.token.AjaxAuthenticationToken
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

/**
 * @author yoonho
 * @since 2023.03.26
 */
class AjaxLoginProcessingFilter: AbstractAuthenticationProcessingFilter(AntPathRequestMatcher("/api/login")) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val om = ObjectMapper()

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        if(!this.isAjax(request!!)) {
            throw IllegalStateException("Authentication is not supported")
        }

        val input = om.readValue(request.reader, AccountInput::class.java)
        if(input.username.isNullOrEmpty() || input.password.isNullOrEmpty()) {
            throw IllegalArgumentException("Username Or Password is empty")
        }

        val ajaxAuthenticationToken = AjaxAuthenticationToken(input.username, input.password)

        return authenticationManager.authenticate(ajaxAuthenticationToken)
    }

    private fun isAjax(request: HttpServletRequest): Boolean {
        if("XMLHttpRequest" == request.getHeader("X-Requested-With")) {
            return true
        }
        return false
    }
}