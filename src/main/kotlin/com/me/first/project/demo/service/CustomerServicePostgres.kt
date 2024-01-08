package com.me.first.project.demo.service

import com.me.first.project.demo.dto.CustomerDetails
import com.me.first.project.demo.mapper.CustomerMapper
import com.me.first.project.demo.repo.AddressRepo
import com.me.first.project.demo.repo.CustomerRepo
import jakarta.transaction.Transactional
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        val currentCustomer = customerRepo.findById(customerId).orElseThrow {
            throw IllegalArgumentException("Customer with id $customerId doesn't exist")
        }
        return runBlocking {
            launch { addressRepo.deleteAll(currentCustomer.addresses) }
            val newCustomer = customerMapper.from(customerDetails)
            newCustomer.id = customerId
            launch { addressRepo.saveAll(newCustomer.addresses) }
            val defer = async { customerRepo.save(newCustomer) }
            return@runBlocking customerMapper.to(defer.await())
        }
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

