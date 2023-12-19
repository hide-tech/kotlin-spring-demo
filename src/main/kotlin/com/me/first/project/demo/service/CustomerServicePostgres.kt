package com.me.first.project.demo.service

import com.me.first.project.demo.dto.CustomerDetails
import com.me.first.project.demo.mapper.CustomerMapper
import com.me.first.project.demo.repo.AddressRepo
import com.me.first.project.demo.repo.CustomerRepo
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerServicePostgres : CustomerService {

    private lateinit var customerRepo: CustomerRepo
    private lateinit var addressRepo: AddressRepo
    private lateinit var customerMapper: CustomerMapper

    @Autowired
    fun initialize(
        customerRepo: CustomerRepo,
        addressRepo: AddressRepo,
        customerMapper: CustomerMapper
    ) {
        this.customerRepo = customerRepo
        this.addressRepo = addressRepo
        this.customerMapper = customerMapper
    }

    override fun getCustomers(): List<CustomerDetails> {
        return customerRepo.findAll().map { customerMapper.to(it) }
    }

    override fun getCustomerById(customerId: Long): CustomerDetails {
        return customerMapper.to(customerRepo.findById(customerId)
            .orElseThrow {
                throw IllegalArgumentException(
                    "Customer " +
                            "with id $customerId doesn't exist"
                )
            })
    }

    @Transactional
    override fun updateCustomer(
        customerId: Long,
        customerDetails: CustomerDetails
    ): CustomerDetails {
        var currentCustomer = customerRepo.findById(customerId).orElseThrow {
            throw IllegalArgumentException("Customer with id $customerId doesn't exist")
        }
        addressRepo.deleteAll(currentCustomer.addresses)
        var newCustomer = customerMapper.from(customerDetails)
        newCustomer.id = customerId
        addressRepo.saveAll(newCustomer.addresses)
        newCustomer = customerRepo.save(newCustomer)
        return customerMapper.to(newCustomer)
    }

    @Transactional
    override fun createCustomer(customerDetails: CustomerDetails): CustomerDetails {
        var customer = customerMapper.from(customerDetails)
        customer = customerRepo.save(customer)
        addressRepo.saveAll(customer.addresses)
        return customerMapper.to(customer)
    }

    @Transactional
    override fun deleteCustomer(customerId: Long) {
        customerRepo.deleteById(customerId)
    }
}

