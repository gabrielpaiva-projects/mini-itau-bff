package com.miniitaubff.repository

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.infra.ErrorResponse
import com.miniitaubff.model.response.Customer
import com.miniitaubff.model.response.LoginData
import org.springframework.stereotype.Repository

@Repository
class LoginRepository(private val firebaseApp: FirebaseApp) {

    fun login(account: Int, agency: Int, accountDigit: Int, password: String): BackendResponse<*> {
        val firestore = FirestoreClient.getFirestore(firebaseApp)
        val documentReferente = firestore.collection("loginData")
        var currentAccount: LoginData? = null

        documentReferente.get().get().documents.forEach {
            val response = it.toObject(LoginData::class.java)

            if (response.accountNumber == account
                    && response.accountDigit == accountDigit
                    && response.agencyNumber == agency) {

                if (password != response.password) {
                    return BackendResponse(
                            statusCode = 422,
                            success = null,
                            error = ErrorResponse(
                                    "Sua senha está incorreta.",
                            )
                    )
                }
                currentAccount = response
            }
        }


        return currentAccount?.let {
            BackendResponse(
                    statusCode = 200,
                    success = getCustomer(it),
                    error = null
            )
        } ?: run {
            BackendResponse(
                    statusCode = 422,
                    success = null,
                    error = ErrorResponse(
                            "Não existe nenhum usuário em nossa base com os dados inseridos",
                    )
            )
        }

    }

    private fun getCustomer(currentAccount: LoginData): Customer? {
        val firestore = FirestoreClient.getFirestore(firebaseApp)
        val documentReferente = firestore.collection("users")

        return documentReferente.document(currentAccount.accountId).get().get().toObject(Customer::class.java)
    }
}