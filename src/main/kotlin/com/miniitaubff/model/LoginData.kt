package com.miniitaubff.model

data class LoginData(
        val accountDigit: Int,
        val accountId: String,
        val accountNumber: Int,
        val agencyNumber: Int,
        val password: String
) {
    constructor(): this(0,"", 0, 0, "")
}