package com.miniitaubff

import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.service.TransactionServiceProvider
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class TransactionController(val provider: TransactionServiceProvider) {

    @PostMapping("/sendTransaction")
    fun sendTransaction(
            @RequestHeader
            amount: Double,
            @RequestHeader
            currentUserId: String,
            @RequestHeader
            destinationDocument: String? = null,
            @RequestHeader
            destinationPhoneNumber: String? = null): BackendResponse<*> {

        return provider.sendPIX(amount, currentUserId, destinationPhoneNumber, destinationDocument)

    }
}