package com.miniitaubff

import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.model.response.Customer
import com.miniitaubff.model.response.CustomerAccount
import com.miniitaubff.service.AccountServiceProvider
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class AccountController(val provider: AccountServiceProvider) {
    @PostMapping("/newAccount")
    fun createAccount(
            @RequestHeader
            customerDocument: String,
            @RequestHeader
            customerMail: String,
            @RequestHeader
            customerName: String,
            @RequestHeader
            customerPhoneNumber: String,
            @RequestHeader
            customerPhoneNumberAreaCode: String,
            @RequestHeader
            accountNumber: Int,
            @RequestHeader
            agency: Int,
            @RequestHeader
            accountDigit: Int,
            @RequestHeader
            password: String
    ): BackendResponse<*> {
        val customerAccount =  CustomerAccount(11700.0, 100.0, listOf())
        return provider.createNewUser(
                Customer(customerAccount,
                        customerDocument,
                        customerMail,
                        customerName,
                        customerPhoneNumber,
                        UUID.randomUUID().toString()
                ),
                accountNumber,
                accountDigit,
                agency, password
        )
    }

}