package com.yoonho.corespringsecurity.account.service

import com.yoonho.corespringsecurity.account.domain.Account
import com.yoonho.corespringsecurity.account.repository.AccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author yoonho
 * @since 2023.03.22
 */
@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun createUser(account: Account) {
        // 패스워드 암호화
        account.password = passwordEncoder.encode(account.password)
        //

        accountRepository.save(account)
    }

}