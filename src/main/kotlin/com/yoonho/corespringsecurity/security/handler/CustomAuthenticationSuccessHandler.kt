package com.yoonho.corespringsecurity.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.stereotype.Component

/**
 * @author yoonho
 * @since 2023.03.26
 */
@Component
class CustomAuthenticationSuccessHandler: SimpleUrlAuthenticationSuccessHandler() {
    private val log = LoggerFactory.getLogger(this::class.java)

    private val requestCache = HttpSessionRequestCache()
    private val redirectStrategy = DefaultRedirectStrategy()

    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        val savedRequest = requestCache.getRequest(request, response)
        log.info(" >>> [onAuthenticationSuccess] savedRequest: $savedRequest")
        savedRequest?.let {
            val targetUrl = savedRequest.redirectUrl
            log.info(" >>> [onAuthenticationSuccess] targetUrl: $targetUrl")
            redirectStrategy.sendRedirect(request, response, targetUrl)
        } ?: redirectStrategy.sendRedirect(request, response, defaultTargetUrl)
    }
}