package com.miniitaubff.infra

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader


@Configuration
class FirebaseConfig {

    @Autowired
    private val resourceLoader: ResourceLoader? = null

    @Bean
    fun firebaseApp(): FirebaseApp {
        val classLoader = javaClass.classLoader
        val inputStream = classLoader.getResourceAsStream("firebase.json")

        val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()

        return FirebaseApp.initializeApp(options)
    }
}