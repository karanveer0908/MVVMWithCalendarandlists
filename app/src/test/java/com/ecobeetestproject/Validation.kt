package com.ecobeetestproject

import com.ecobeetestproject.data.Validation
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class Validation {


    @Test
    fun `empty title description and  date returns false`() {
        val resultValue = Validation.isValidFields(
            "",
            "", ""
        )
        assertThat(resultValue).isFalse()
    }

    @Test
    fun `valid title description and date returns true`() {
        val resultValue = Validation.isValidFields(
            "testDate1",
            "TestDescrip", "testdate"
        )
        assertThat(resultValue).isTrue()
    }

    @Test
    fun checkValues() {


        for (i in 0..100) {
//            if (i % 5 == 0) {
//                print("five")
//
//            }
            if (i % 4 == 0) {
//                print(i)

//                print("four")

            }
            if (i % 5 == 0 && i % 4 == 0) {
//                print("fiveandfour")
                print(i)

            }
        }
    }

    @Test
    fun checkValuesByadding() {


        for (i in 0..100) {
            print(i)
            var a = 5
            var b = 4
            if (i == a) {
                a = a + 5
                print("five")

            }

            if (i == b) {
                b = b + 4
                print("four")

            }

            if (i == a && i == b) {
                print("fiveandfour")
            }
        }
    }
}


