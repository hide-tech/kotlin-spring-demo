package com.me.first.project.demo.repo

import com.me.first.project.demo.domain.Customer
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CustomerRepo: JpaRepository<Customer, Long> {

    @EntityGraph(attributePaths = ["addresses"])
    override fun findById(customerId: Long): Optional<Customer>

    @EntityGraph(attributePaths = ["addresses"])
    override fun findAll(): List<Customer>
}