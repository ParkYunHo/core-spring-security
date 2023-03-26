package com.yoonho.corespringsecurity.security.details

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.AuthenticationDetailsSource
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component

/**
 * @author yoonho
 * @since 2023.03.26
 */
@Component
class FormAuthenticationDetailsSource: AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

    override fun buildDetails(context: HttpServletRequest?): WebAuthenticationDetails =
        FormWebAuthenticationDetails(context!!)
}