package com.miniitaubff.model.response

data class Customer(
        var customerAccount: CustomerAccount,
        val customerDocument: String,
        val customerMail: String,
        val customerName: String,
        val customerPhoneNumber: String,
        val userId: String
) {

    constructor() : this(
            CustomerAccount(),
            "",
            "",
            "",
            "",
            "",
    )
}

data class CustomerAccount(
        val currentAccountBalance: Double,
        val currentAccountBalanceExtraLimit: Double,
        val transactions: ArrayList<Transaction>
) {

    constructor(): this(0.0, 0.0, arrayListOf())
}

data class Transaction(
        val receivedCustomerId: String,
        val transactionAmount: Double,
        val transactionStatus: String,
        val transactionType: String
) {
    constructor(): this("", 0.0, "", "")
}