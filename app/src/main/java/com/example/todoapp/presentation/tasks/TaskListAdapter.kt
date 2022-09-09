package com.example.todoapp.presentation.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.BR
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTaskBinding
import com.example.todoapp.network.models.TasksItem

class TaskListAdapter(private val viewModel: TaskListViewModel) : RecyclerView.Adapter<TaskViewDataBindingHolder>() {

    private val items = ArrayList<TasksItem>()

    fun setItems(items: ArrayList<TasksItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewDataBindingHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_task, parent, false
        )
        return TaskViewDataBindingHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewDataBindingHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

}
class TaskViewDataBindingHolder(private val itemBinding: ItemTaskBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item: TasksItem, viewModel: TaskListViewModel) {
        itemBinding.setVariable(BR.taskItem, item)
        itemBinding.viewModel = viewModel
        itemBinding.executePendingBindings()
    }
}
