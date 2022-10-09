package com.ecobeetestproject.base.listners

import com.ecobeetestproject.model.TaskListM

interface AddTaskDataCallback  {

   fun onTaskAdded(addedTask : TaskListM)

   fun onValidationError ()
}