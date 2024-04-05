package com.miniitaubff.infra

class BackendResponse<T>(
        val statusCode: Int,
        val success: T?, val error: ErrorResponse?
) {
}

data class ErrorResponse(
        val errorDescription: String,
)