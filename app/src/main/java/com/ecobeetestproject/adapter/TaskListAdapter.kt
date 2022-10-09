package com.ecobeetestproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ecobeetestproject.base.listners.RecyclerItemListener
import com.ecobeetestproject.databinding.RecyclerTasklistBinding
import com.ecobeetestproject.model.TaskListM

class TaskListAdapter(

    private val recyclerItem:RecyclerItemListener,

    private val tasks: List<TaskListM>
) :
    RecyclerView.Adapter<TaskListAdapter.TasksViewHolder>() {

    private lateinit var binding: RecyclerTasklistBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        binding =
            RecyclerTasklistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val taskListPos = tasks[position]
        holder.bind(taskListPos)
        binding.root.setOnClickListener {
            recyclerItem.onItemSelected(position, taskListPos)
        }
    }

    class TasksViewHolder(
        private val binding: RecyclerTasklistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskListM) {
            binding.taskModel = taskModel
        }
    }

    override fun getItemCount(): Int = tasks.size

}