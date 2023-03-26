package com.yoonho.corespringsecurity.security.details

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component

/**
 * @author yoonho
 * @since 2023.03.26
 */
class FormWebAuthenticationDetails constructor(
    request: HttpServletRequest
): WebAuthenticationDetails(request) {

    private val secretKey: String = request.getParameter("secret_key")

    fun getSecretKey(): String = this.secretKey
}