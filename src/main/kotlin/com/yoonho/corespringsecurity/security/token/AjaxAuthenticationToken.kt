package com.yoonho.corespringsecurity.security.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * @author yoonho
 * @since 2023.03.26
 */
class AjaxAuthenticationToken: AbstractAuthenticationToken {

    private val ajaxPrincipal: Any
    private val ajaxCredentials: Any

    constructor(): super(null) {
        ajaxPrincipal = ""
        ajaxCredentials = ""
        this.isAuthenticated = false
    }

    constructor(principal: Any, credentials: Any) : super(null) {
        ajaxPrincipal = principal
        ajaxCredentials = credentials
        this.isAuthenticated = false
    }

    constructor(principal: Any, credentials: Any, authorities: MutableCollection<out GrantedAuthority>): super(authorities) {
        ajaxPrincipal = principal
        ajaxCredentials = credentials
        this.isAuthenticated = true
    }

    override fun getCredentials(): Any =
        this.ajaxCredentials

    override fun getPrincipal(): Any =
        this.ajaxPrincipal

    override fun setAuthenticated(isAuthenticated: Boolean) {
        if(isAuthenticated) {
            throw IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead")
        }

        super.setAuthenticated(false)
    }

}