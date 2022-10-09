package com.ecobeetestproject

import com.ecobeetestproject.utils.Validator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TaskDetailViewModelTest {

    val validTestData = "Test"
    val invalidTestData = ""


    @Before
    fun setup() {}

    @Test
    fun onSubmitClickInvalidData() {
        assertEquals(Validator.validateTaskCreate(invalidTestData, invalidTestData, invalidTestData), false)
    }

    @Test
    fun onSubmitClickWithValidData() {
        assertEquals(Validator.validateTaskCreate(validTestData, validTestData, validTestData), true)
    }
}