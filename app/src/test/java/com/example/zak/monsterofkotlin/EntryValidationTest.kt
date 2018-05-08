package com.example.zak.monsterofkotlin

import org.junit.Assert.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test


class EntryValidationTest {
    private val main = MainActivity()

    @Test
    fun validateInput_isTrue(){
        assertThat(
                main.inputValidate("Test"), `is`(true))
    }

    @Test
    fun validateInput_Empty(){
        assertThat(main.inputValidate(""), `is`(false))
    }

    @Test
    fun validateInput_Numberic(){
        assertThat(main.inputValidate("98765"), `is`(false))
    }
}

