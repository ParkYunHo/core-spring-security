package com.yoonho.corespringsecurity.api.home

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author yoonho
 * @since 2023.03.22
 */
@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String =
        "home"
}