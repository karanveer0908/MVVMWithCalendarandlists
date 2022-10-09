package com.ecobeetestproject.viewmodel

import androidx.lifecycle.ViewModel
import com.ecobeetestproject.base.listners.AddTaskDataCallback
import com.ecobeetestproject.model.TaskListM
import com.ecobeetestproject.utils.Validator.validateTaskCreate

class TasksDetailViewModel : ViewModel() {

    var mTaskTitle = ""
    var mTaskDescription = ""
    var mTaskDate = ""

    var addTaskDataCallback: AddTaskDataCallback? = null

    fun onSubmitClick() {
        if (validateTaskCreate(mTaskTitle, mTaskDescription, mTaskDate)) {
            val taskDetail = TaskListM(mTaskTitle, mTaskDescription, mTaskDate)
            addTaskDataCallback?.onTaskAdded(taskDetail)
        } else {
            addTaskDataCallback?.onValidationError()
        }
    }

    fun setTaskDetail(taskDetail: TaskListM?) {
        taskDetail?.let {
            mTaskTitle = it.mTitle
            mTaskDescription = it.mDescription
            mTaskDate = it.mDatePick
        }
    }

}


