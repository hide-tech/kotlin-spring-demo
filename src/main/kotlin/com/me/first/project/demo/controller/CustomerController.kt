package com.me.first.project.demo.controller

import com.me.first.project.demo.dto.CustomerDetails
import com.me.first.project.demo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    //@set:Autowired
    private lateinit var customerService: CustomerService

    @Autowired
    fun initialize(customerService: CustomerService) {
        this.customerService = customerService
    }

    @GetMapping("/v1/customers/{id}")
    fun getCustomerById(@PathVariable("id") customerId: Long): CustomerDetails {
        return customerService.getCustomerById(customerId)
    }

    @GetMapping("/v1/customers")
    fun getAllCustomers(): List<CustomerDetails> {
        return customerService.getCustomers()
    }

    @PostMapping("/v1/customers")
    fun createNewCustomer(@RequestBody customerDetails: CustomerDetails): CustomerDetails {
        return customerService.createCustomer(customerDetails)
    }

    @PutMapping("/v1/customers/{id}")
    fun updateCustomer(
        @PathVariable("id") customerId: Long,
        @RequestBody customerDetails: CustomerDetails
    ): CustomerDetails {
        return customerService.updateCustomer(customerId, customerDetails)
    }

    @DeleteMapping("/v1/customers/{id}")
    fun deleteCustomer(@PathVariable("id") customerId: Long) {
        customerService.deleteCustomer(customerId)
    }
}