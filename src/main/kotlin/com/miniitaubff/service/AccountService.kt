package com.miniitaubff.service

import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.model.response.Customer
import com.miniitaubff.repository.AccountRepository
import org.springframework.stereotype.Service

interface AccountServiceProvider {
    fun createNewUser(customer: Customer,
                      accountNumber: Int,
                      accountDigit: Int,
                      agency: Int,
                      password: String): BackendResponse<*>
}

@Service
class AccountService(private val repository: AccountRepository): AccountServiceProvider {
    override fun createNewUser(customer: Customer,
                               accountNumber: Int,
                               accountDigit: Int,
                               agency: Int,
                               password: String): BackendResponse<*> {

        return repository.createNewUser(customer, accountNumber, accountDigit, agency, password)
    }
}