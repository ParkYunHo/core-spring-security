package com.yoonho.corespringsecurity.security.auth

import com.yoonho.corespringsecurity.account.repository.AccountRepository
import org.slf4j.LoggerFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author yoonho
 * @since 2023.03.23
 */
@Service
class CustomUserDetailsService(
    private val accountRepository: AccountRepository
): UserDetailsService {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun loadUserByUsername(username: String): UserDetails =
        accountRepository.findByUsername(username)?.let {
            log.info(" >>> [loadUserByUsername] account - username: ${it.username}, password: ${it.password}, role: ${it.role}")
            AccountContext(it, mutableSetOf(SimpleGrantedAuthority(it.role)))
        } ?: throw UsernameNotFoundException("UserNameNotFoundException")

}