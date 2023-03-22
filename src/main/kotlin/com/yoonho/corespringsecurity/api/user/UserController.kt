package com.yoonho.corespringsecurity.api.user

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.yoonho.corespringsecurity.account.domain.Account
import com.yoonho.corespringsecurity.account.service.AccountService
import com.yoonho.corespringsecurity.api.user.dto.AccountInput
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

/**
 * @author yoonho
 * @since 2023.03.22
 */
@Controller
class UserController(
    private val accountService: AccountService,
    private val objectMapper: ObjectMapper
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/mypage")
    fun myPage(): String =
        "user/mypage"

    @GetMapping("/users")
    fun createUserPage(): String =
        "user/login/register"

    @PostMapping("/users")
    fun createUser(input: AccountInput): String {
        val entity = objectMapper.convertValue(input, object : TypeReference<Account>(){} )
        accountService.createUser(entity)

        return "redirect:/"
    }
}