package com.yoonho.corespringsecurity.account.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.ToString

/**
 * @author yoonho
 * @since 2023.03.22
 */
@Entity
@Table
class Account(
    @Id
    @GeneratedValue
    val id: Long,

    val username: String,
    var password: String,
    val email: String,
    val age: String,
    val role: String
)