package com.miniitaubff.service

import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.repository.TransactionRepository
import org.springframework.stereotype.Service

interface TransactionServiceProvider {
    fun sendPIX(amount: Double, currentUserId: String, destinationPhoneNumber: String?, destinationDocument: String?): BackendResponse<*>
}

@Service
class TransactionService(private val repository: TransactionRepository): TransactionServiceProvider {
    override fun sendPIX(amount: Double, currentUserId: String, destinationPhoneNumber: String?, destinationDocument: String?): BackendResponse<*> {
        return repository.sendPIX(amount, currentUserId, destinationPhoneNumber, destinationDocument)
    }
}