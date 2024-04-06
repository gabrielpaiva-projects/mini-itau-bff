package com.miniitaubff.repository

import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import com.miniitaubff.infra.BackendResponse
import com.miniitaubff.infra.ErrorResponse
import com.miniitaubff.model.response.Customer
import com.miniitaubff.model.response.LoginData
import com.miniitaubff.model.response.Transaction
import org.springframework.stereotype.Repository

@Repository
class TransactionRepository(private val firebaseApp: FirebaseApp) {

    fun sendPIX(amount: Double, currentUserId: String, destinationPhoneNumber: String?, destinationDocument: String?): BackendResponse<*> {
        val firestore = FirestoreClient.getFirestore(firebaseApp)
        val documentReferente = firestore.collection("users")
        val loginDataReference = firestore.collection("loginData")

        val currentUser = documentReferente.document(currentUserId).get().get().toObject(Customer::class.java)
        var destinationUser: LoginData? = null

        loginDataReference.get().get().documents.forEach {
            val response = it.toObject(LoginData::class.java)
            val currentUser = documentReferente.document(response.accountId).get().get().toObject(Customer::class.java)

            destinationDocument?.let {
                if (it == currentUser?.customerDocument) {
                    destinationUser = response
                }
            }

            destinationPhoneNumber?.let {
                if (it == currentUser?.customerPhoneNumber) {
                    destinationUser = response
                }
            }
        }

        destinationUser?.let { loginData ->
            currentUser?.let { customer ->
                if (customer.customerAccount.currentAccountBalance > amount) {
                    return finishTransaction(amount, loginData.accountId, currentUserId)
                }

                return BackendResponse(422, success = null, error = ErrorResponse("Você não possuí saldo suficiente para a transação."))
            }
        }

        return BackendResponse(422, success = null, error = ErrorResponse("Erro ao recuperar conta do usuário."))

    }


    private fun finishTransaction(amountValue: Double, destinationCustomerId: String, currentUserId: String): BackendResponse<*> {
        val firestore = FirestoreClient.getFirestore(firebaseApp)
        val documentReferente = firestore.collection("users")

        val currentUser = documentReferente.document(currentUserId).get().get().toObject(Customer::class.java)
        val destinationCustomer = documentReferente.document(destinationCustomerId).get().get().toObject(Customer::class.java)

        destinationCustomer?.let {

            var transaction = it.customerAccount.transactions
            transaction.add(Transaction(currentUserId, amountValue, "RECEIVED", "PIX"))
            documentReferente
                    .document(destinationCustomerId)
                    .update(mapOf(
                            "customerAccount.currentAccountBalance" to it.customerAccount.currentAccountBalance + amountValue,
                            "customerAccount.transactions" to  transaction
                    )
                    )
        } ?: run {
            return BackendResponse(422, null, ErrorResponse("Erro ao buscar conta."))
        }

        currentUser?.let {
            var transaction = it.customerAccount.transactions
            transaction.add(Transaction(destinationCustomerId, amountValue, "SENDED", "PIX"))
            documentReferente
                    .document(currentUserId)
                    .update(mapOf(
                            "customerAccount.currentAccountBalance" to it.customerAccount.currentAccountBalance - amountValue,
                            "customerAccount.transactions" to transaction
                    )
                    )
        } ?: run {
            return BackendResponse(422, null, ErrorResponse("Erro ao buscar conta."))
        }


        return BackendResponse(200, success = "Pix realizado com sucesso .", null)

    }

}