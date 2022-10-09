package com.ecobeetestproject.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ecobeetestproject.R
import com.ecobeetestproject.base.listners.AddTaskDataCallback
import com.ecobeetestproject.databinding.TaskDetailActivityBinding
import com.ecobeetestproject.model.TaskListM
import com.ecobeetestproject.utils.AppConstants.PARAM_POSITION
import com.ecobeetestproject.utils.AppConstants.PARAM_TASK_DETAIL
import com.ecobeetestproject.viewmodel.TasksDetailViewModel
import java.util.*

class TaskDetailScreenAT : BaseActivity<TaskDetailActivityBinding>(R.layout.task_detail_activity),
    AddTaskDataCallback {

    private lateinit var taskDetailBinding: TaskDetailActivityBinding

    lateinit var viewModel: TasksDetailViewModel

    private var taskDetail: TaskListM? = null

    private var mUpdatePosition = 0

    override fun initComponents(savedInstanceState: Bundle?, binding: TaskDetailActivityBinding) {
        viewModel = ViewModelProvider(this).get(TasksDetailViewModel::class.java)
        taskDetailBinding = binding
        taskDetailBinding.taskviewmodel = viewModel
        viewModel.addTaskDataCallback = this

        getDataFromIntent(intent)

        setUpClicks()
    }


    @SuppressLint("SetTextI18n")
    private fun setUpClicks() {


        taskDetailBinding.tvTaskDate.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val dpd = DatePickerDialog(
                this,
                { _, selectedYear, monthOfYear, dayOfMonth ->
                    taskDetailBinding.tvTaskDate.text = "$dayOfMonth $monthOfYear, $selectedYear"
                }, year, month, day
            )

            dpd.show()
        }
    }

    private fun getDataFromIntent(intent: Intent) {
        intent.apply {
            if (hasExtra(PARAM_POSITION)) {
                mUpdatePosition = getIntExtra(PARAM_POSITION, mUpdatePosition)
                taskDetail = getSerializableExtra(PARAM_TASK_DETAIL) as TaskListM?
                viewModel.setTaskDetail(taskDetail)
                taskDetailBinding.tvSubmit.text = getString(R.string.update)
            }
        }
    }

    override fun onTaskAdded(addedTask: TaskListM) {
        val intent = Intent()
        if (taskDetail != null) {
            intent.putExtra(PARAM_POSITION, mUpdatePosition)
        }
        intent.putExtra(PARAM_TASK_DETAIL, addedTask)
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onValidationError() {
        showMessage(taskDetailBinding.root, getString(R.string.error_all_detail))
    }




}