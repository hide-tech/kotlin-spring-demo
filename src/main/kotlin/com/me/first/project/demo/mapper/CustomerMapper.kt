package com.me.first.project.demo.mapper

import com.me.first.project.demo.domain.Address
import com.me.first.project.demo.domain.Customer
import com.me.first.project.demo.dto.AddressDetails
import com.me.first.project.demo.dto.CustomerDetails
import org.springframework.stereotype.Component

@Component
class CustomerMapper {
    fun from(customerDetails: CustomerDetails): Customer {
        val customer = Customer(
            customerDetails.id, customerDetails.firstName,
            customerDetails.lastName, customerDetails.email, customerDetails.phone
        )
        customer.addresses = customerDetails.addresses.map { fromDetails(it, customer) }
        return customer
    }

    private fun fromDetails(addressDetails: AddressDetails, customer: Customer): Address {
        return Address(
            addressDetails.id, addressDetails.country, addressDetails.city,
            addressDetails.street, addressDetails.building, addressDetails.office,
            addressDetails.zip, customer
        )
    }

    fun to(customer: Customer): CustomerDetails {
        val customerDetails = CustomerDetails(customer.id, customer.firstName, customer.lastName,
            customer.email, customer.phone)
        customerDetails.addresses = customer.addresses.map { toDetails(it) }
        customerDetails.createdAt = customer.createdAt
        return customerDetails
    }

    private fun toDetails(address: Address): AddressDetails {
        return AddressDetails(address.id, address.country, address.city, address.street,
            address.building, address.office, address.zip, address.createdAt)
    }
}