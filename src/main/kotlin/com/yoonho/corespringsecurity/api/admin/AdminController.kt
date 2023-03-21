package com.yoonho.corespringsecurity.api.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AdminController {

    @GetMapping("/config")
    @ResponseBody
    fun config(): String =
        "config"


}