package com.me.first.project.demo.dto

import java.time.LocalDateTime

data class CustomerDetails(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    var email: String,
    var phone: String?,
    var addresses: List<AddressDetails> = ArrayList<AddressDetails>(),
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
}
