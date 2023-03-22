package com.yoonho.corespringsecurity.api.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author yoonho
 * @since 2023.03.22
 */
@Controller
class AdminController {

    @GetMapping("/config")
    fun config(): String =
        "admin/config"


}