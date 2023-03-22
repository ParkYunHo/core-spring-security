package com.yoonho.corespringsecurity.api.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserController {

    @GetMapping("/mypage")
    fun myPage(): String =
        "user/mypage"
}