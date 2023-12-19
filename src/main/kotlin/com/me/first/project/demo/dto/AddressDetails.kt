package com.me.first.project.demo.dto

import java.time.LocalDateTime

data class AddressDetails(
    val id: Long?,
    val country: String,
    val city: String,
    val street: String,
    val building: String?,
    val office: String?,
    val zip: Int,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
}
