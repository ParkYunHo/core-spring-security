package com.yoonho.corespringsecurity.account.repository

import com.yoonho.corespringsecurity.account.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author yoonho
 * @since 2023.03.22
 */
interface AccountRepository: JpaRepository<Account, Long> {
    fun findByUsername(username: String): Account?
}