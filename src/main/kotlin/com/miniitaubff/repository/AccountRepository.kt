package com.miniitaubff.repository

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.infra.ErrorResponse
import com.miniitaubff.model.response.Customer
import com.miniitaubff.model.response.LoginData
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class AccountRepository(private val firebaseApp: FirebaseApp) {

    fun createNewUser(customer: Customer,
                      accountNumber: Int,
                      accountDigit: Int,
                      agency: Int,
                      password: String): BackendResponse<*> {
        val firestore = FirestoreClient.getFirestore(firebaseApp)
        val loginData = firestore.collection("loginData")
        val userId = customer.userId
        val documentReferente = firestore.collection("users")

        loginData.get().get().documents.forEach {
            val response = it.toObject(LoginData::class.java)

            if (response.accountNumber == accountNumber
                    && response.accountDigit == accountDigit
                    && response.agencyNumber == agency) {

                return BackendResponse(
                        422,
                        success = null,
                        error = ErrorResponse("Esse usuário já existe em nossa base de dados.")
                )
            }
        }

        loginData.document(userId).create(LoginData(accountDigit, userId, accountNumber,agency, password)).get()
        documentReferente.document(userId).create(customer).get()

        return BackendResponse(
                200,
                success = "Conta criada com sucesso.",
                error = null
        )
    }
}