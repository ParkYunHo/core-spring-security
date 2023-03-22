package com.yoonho.corespringsecurity.api.home

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String =
        "home"
}