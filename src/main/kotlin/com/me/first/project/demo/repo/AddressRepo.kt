package com.me.first.project.demo.repo

import com.me.first.project.demo.domain.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepo: JpaRepository<Address, Long> {
}