package com.yoonho.corespringsecurity.api.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MessageController {

    @GetMapping("/messages")
    fun messages(): String =
        "user/messages"

}