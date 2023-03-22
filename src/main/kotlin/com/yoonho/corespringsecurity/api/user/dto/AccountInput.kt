package com.yoonho.corespringsecurity.api.user.dto

/**
 * @author yoonho
 * @since 2023.03.22
 */
data class AccountInput(
    val username: String,
    val password: String,
    val email: String,
    val age: String,
    val role: String
)
