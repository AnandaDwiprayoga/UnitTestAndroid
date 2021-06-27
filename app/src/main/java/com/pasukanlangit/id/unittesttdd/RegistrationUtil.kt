package com.pasukanlangit.id.unittesttdd

object RegistrationUtil {

    private val existingUsers = listOf("Peter","Carl")

    /**
    * this function will return false if
     * 1. the username/password is empty
     * 2. the username is already taken
     * 3. the confirmed password is not the same as the real password
     * 4. the password contains less than 2 character
    **/
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmedPassword: String
    ): Boolean {
        if(username.isEmpty() || password.isEmpty()) return false
        if(password.length < 2) return false
        if(confirmedPassword != password) return false
        if(username in existingUsers) return false
        return true
    }
}