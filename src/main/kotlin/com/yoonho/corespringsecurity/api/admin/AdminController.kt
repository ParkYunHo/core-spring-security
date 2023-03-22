package com.yoonho.corespringsecurity.api.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminController {

    @GetMapping("/config")
    fun config(): String =
        "admin/config"


}