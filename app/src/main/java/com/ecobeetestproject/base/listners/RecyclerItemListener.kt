package com.ecobeetestproject.base.listners

import com.ecobeetestproject.model.TaskListM

interface RecyclerItemListener {


    fun onItemSelected(pos :Int, taskDetail : TaskListM)

}