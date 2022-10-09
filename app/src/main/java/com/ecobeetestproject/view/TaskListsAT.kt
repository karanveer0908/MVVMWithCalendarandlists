package com.ecobeetestproject.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.ecobeetestproject.R
import com.ecobeetestproject.adapter.TaskListAdapter
import com.ecobeetestproject.base.listners.RecyclerItemListener
import com.ecobeetestproject.databinding.TasklistActivityBinding
import com.ecobeetestproject.model.TaskListM
import com.ecobeetestproject.utils.AppConstants.PARAM_POSITION
import com.ecobeetestproject.utils.AppConstants.PARAM_TASK_DETAIL

class TaskListsAT : BaseActivity<TasklistActivityBinding>(R.layout.tasklist_activity), RecyclerItemListener {

    private lateinit var taskListBinding: TasklistActivityBinding

    lateinit var tasksList: ArrayList<TaskListM>

    lateinit var taskListAdapter : TaskListAdapter

    private val getTaskDetailResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val taskDetail = it.data?.getSerializableExtra(PARAM_TASK_DETAIL) as TaskListM
                if (it.data?.hasExtra(PARAM_POSITION) == true) {
                    val position = it.data?.getIntExtra(PARAM_POSITION, 0)!!
                    tasksList[position] = taskDetail
                    taskListAdapter.notifyItemChanged(position)
                    showMessage(taskListBinding.root, getString(R.string.task_updated_successfully))
                } else {
                    tasksList.add(taskDetail)
                    taskListAdapter.notifyItemInserted(tasksList.size - 1)
                    showMessage(taskListBinding.root, getString(R.string.task_added_successfully))
                }
            }
        }

    override fun initComponents(savedInstanceState: Bundle?, binding: TasklistActivityBinding) {
        this.taskListBinding = binding
        initAdapter()

        taskListBinding.floatingActionButton.setOnClickListener {
            moveToDetailScreen()
        }
    }

    private fun initAdapter() {
        tasksList = ArrayList()
        taskListAdapter = TaskListAdapter(this, tasksList)
        taskListBinding.rvTasks.adapter = taskListAdapter
    }

    override fun onItemSelected(pos: Int, taskDetail: TaskListM) {
        moveToDetailScreen(pos, taskDetail)
    }

    private fun moveToDetailScreen(pos: Int? = null, taskDetail: TaskListM? = null) {
        val intent = Intent(this, TaskDetailScreenAT::class.java)
        if (pos != null) {
            intent.putExtra(PARAM_TASK_DETAIL, taskDetail)
            intent.putExtra(PARAM_POSITION, pos)
        }
        getTaskDetailResult.launch(intent)
    }

}