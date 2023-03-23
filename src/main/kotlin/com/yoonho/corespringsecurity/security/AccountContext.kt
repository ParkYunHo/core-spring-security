package com.yoonho.corespringsecurity.security

import com.yoonho.corespringsecurity.account.domain.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * @author yoonho
 * @since 2023.03.23
 */
class AccountContext constructor(
    account: Account,
    authorities: MutableCollection<out GrantedAuthority>
): User(account.username, account.password, authorities)