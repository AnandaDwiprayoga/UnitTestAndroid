package com.pasukanlangit.id.unittesttdd

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `should return false if username is empty`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "",
            password = "1234",
            "1234"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `should return true if username valid and password match confirmed password`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Yoga",
            password = "1234",
            "1234"
        )

        assertThat(result).isTrue()
    }


    @Test
    fun `should return false if username exist`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Peter",
            password = "1234",
            "1234"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `should return false if password is empty`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Peter",
            password = "",
            "1234"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `should return false if confirmed password not matched`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Peter",
            password = "1234",
            "4321"
        )

        assertThat(result).isFalse()
    }

    @Test
    fun `should return false if password less than 2 character`(){
        val result = RegistrationUtil.validateRegistrationInput(
            username = "Bejo",
            password = "1",
            "1"
        )

        assertThat(result).isFalse()
    }

}