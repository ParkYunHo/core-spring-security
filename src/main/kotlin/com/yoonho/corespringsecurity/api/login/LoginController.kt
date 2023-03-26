package com.yoonho.corespringsecurity.api.login

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * @author yoonho
 * @since 2023.03.23
 */
@Controller
class LoginController {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/login")
    fun login(
        @RequestParam(value = "error", required = false) error: String?,
        @RequestParam(value = "exception", required = false) exception: String?,
        model: Model
    ): String {

        model.addAttribute("error", error)
        model.addAttribute("exception", exception)

        return "user/login/login"
    }


    @GetMapping("/logout")
    fun logout(request: HttpServletRequest, response: HttpServletResponse): String {
        val auth = SecurityContextHolder.getContext().authentication
        auth?.let {
            log.info(" >>> [logout] Custom Logout Page Processing")
            SecurityContextLogoutHandler().logout(request, response, auth)
        }

        return "redirect:/login"
    }
}