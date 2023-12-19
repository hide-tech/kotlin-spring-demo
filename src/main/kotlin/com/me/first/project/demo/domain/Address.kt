package com.me.first.project.demo.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "addresses")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val country: String,
    val city: String,
    val street: String,
    val building: String?,
    val office: String?,
    val zip: Int,
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    var customer: Customer,
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now()
)