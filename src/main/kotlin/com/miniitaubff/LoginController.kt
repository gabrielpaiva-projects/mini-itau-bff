package com.miniitaubff

import com.miniitaubff.infra.BackendResponse

import org.springframework.web.bind.annotation.*
import com.miniitaubff.service.LoginServiceProvider

@RestController
class LoginController(val provider: LoginServiceProvider) {

    @PostMapping("/login")
    fun login(
        @RequestHeader
        accountNumber: Int,
        @RequestHeader
        agency: Int,
        @RequestHeader
        accountDigit: Int,
        @RequestHeader
        password: String
    ): BackendResponse<*> {
        return provider.login(accountNumber, agency, accountDigit, password)
    }

}