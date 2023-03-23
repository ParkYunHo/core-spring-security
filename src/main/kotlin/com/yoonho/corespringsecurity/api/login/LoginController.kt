package com.yoonho.corespringsecurity.api.login

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author yoonho
 * @since 2023.03.23
 */
@Controller
class LoginController {

    @GetMapping("/login")
    fun login(): String =
        "user/login/login"
}