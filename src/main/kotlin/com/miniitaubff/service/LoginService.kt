package com.miniitaubff.service

import com.miniitaubff.infra.BackendResponse
import org.springframework.stereotype.Service
import com.miniitaubff.repository.LoginRepository

interface LoginServiceProvider {
    fun login(account: Int, agency: Int, accountDigit: Int, password: String): BackendResponse<*>
}

@Service
class LoginService(private val repository: LoginRepository): LoginServiceProvider {

    override fun login(account: Int, agency: Int, accountDigit: Int, password: String): BackendResponse<*> {
        return repository.login(account, agency, accountDigit, password)
    }

}