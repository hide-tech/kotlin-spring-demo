package com.me.first.project.demo.service

import com.me.first.project.demo.dto.CustomerDetails

interface CustomerService {

    fun getCustomers(): List<CustomerDetails>

    fun getCustomerById(customerId: Long): CustomerDetails

    fun updateCustomer(customerId: Long, customerDetails: CustomerDetails): CustomerDetails

    fun createCustomer(customerDetails: CustomerDetails): CustomerDetails

    fun deleteCustomer(customerId: Long)
}