package com.me.first.project.demo.domain

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "customers")
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(name = "first_name")
    val firstName: String,
    @Column(name = "last_name")
    val lastName: String,
    var email: String,
    var phone: String?,
    @OneToMany(mappedBy = "customer", cascade = [CascadeType.REMOVE])
    var addresses: List<Address> = ArrayList<Address>(),
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun update(customer: Customer): Customer {
        return Customer(
            id, customer.firstName, customer.lastName, customer.email,
            customer.phone, customer.addresses, customer.createdAt
        )
    }
}
