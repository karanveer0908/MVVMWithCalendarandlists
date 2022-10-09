package com.ecobeetestproject.utils

object Validator {

    fun validateTaskCreate(mTaskTitle: String,
                           mTaskDescription: String,
                           mTaskDate: String) : Boolean {
        return !(mTaskTitle.isEmpty() || mTaskDescription.isEmpty() || mTaskDate.isEmpty())
    }
}