package com.example.cinesphere.data.user

object AuthManager {
    private var loggedIn: Boolean = false

    fun isLoggedIn(): Boolean = loggedIn

    fun login() {
        loggedIn = true
    }

    fun logout() {
        loggedIn = false
    }
}