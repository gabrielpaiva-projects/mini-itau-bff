//package com.miniitaubff.infra
//
//import com.google.firebase.FirebaseApp
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseToken;
//import jakarta.servlet.FilterChain
//import jakarta.servlet.annotation.WebFilter
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.stereotype.Component
//import org.springframework.web.filter.OncePerRequestFilter
//
//
//@Component
//class TokenInterceptor(): OncePerRequestFilter() {
//    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
//        val authHeader = request.getHeader("Authorization")
//        val token = authHeader?.substringAfter("Bearer ")
//        val getUnloggedEndPoint = request.getHeader("routeType")
//
////        if (token != null) {
////            if (token == "N8vnSCBkgaR7THZ5jfRY") {
////                filterChain.doFilter(request, response)
////            } else {
////                response.status = HttpServletResponse.SC_UNAUTHORIZED
////                response.writer.println("Invalid Token")
////            }
////        } else {
////            if (getUnloggedEndPoint == "XXLGSA-21") {
////                filterChain.doFilter(request, response)
////                return
////            }
////            response.status = HttpServletResponse.SC_UNAUTHORIZED
////            response.writer.println("Authorization Token missing")
////        }
//
//        filterChain.doFilter(request, response)
//    }
//
//}
