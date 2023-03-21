package com.yoonho.corespringsecurity.api.user

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MessageController {

    @GetMapping("/messages")
    @ResponseBody
    fun messages(): String =
        "messages"

}