package com.miniitaubff.model

data class Customer(
        val customerAccount: CustomerAccount,
        val customerDocument: String,
        val customerMail: String,
        val customerName: String,
        val customerPhoneNumber: String,
        val customerPhoneNumberAreaCode: String
) {

    constructor() : this(
            CustomerAccount(),
            "",
            "",
            "",
            "",
            ""
    )
}

data class CustomerAccount(
        val currentAccountBalance: Double,
        val currentAccountBalanceExtraLimit: Double,
        val transactions: List<Transactions>
) {

    constructor(): this(0.0, 0.0, listOf())
}

data class Transactions(
        val transactionAmount: Double,
        val transactionDescription: String,
        val transactionStatus: String,
        val transactionType: String
) {
    constructor(): this(0.0, "", "", "")
}