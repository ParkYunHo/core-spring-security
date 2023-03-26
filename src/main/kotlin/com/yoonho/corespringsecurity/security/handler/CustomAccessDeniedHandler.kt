package com.yoonho.corespringsecurity.security.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

/**
 * @author yoonho
 * @since 2023.03.26
 */
@Component
class CustomAccessDeniedHandler: AccessDeniedHandler {

    private lateinit var errorPage: String

    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?) {
        val url = UriComponentsBuilder.newInstance()
                    .path(errorPage)
                    .queryParam("exception", accessDeniedException?.message)
                    .build().toUriString()

        response?.sendRedirect(url)
    }

    fun setErrorPage(errorPage: String) {
        this.errorPage = errorPage
    }
}