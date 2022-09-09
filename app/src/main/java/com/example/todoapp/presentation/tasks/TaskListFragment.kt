package com.example.todoapp.presentation.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.TaskListFragmentBinding
import com.example.todoapp.utils.Resource
import com.example.todoapp.utils.findNavControllerSafely
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var binding: TaskListFragmentBinding
    private lateinit var adapter: TaskListAdapter
    private val viewModel: TaskListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecylceView()
        setUpObservers()
        setUpRetry()
    }

    private fun setUpRecylceView() {
        adapter = TaskListAdapter(viewModel)
        binding.todoList.layoutManager = LinearLayoutManager(requireContext())
        binding.todoList.adapter = adapter

        viewModel.onDetailScreenClicked.observe(viewLifecycleOwner) {
            it?.let{
                viewModel.onTaskDetailNavigationComplete()
                findNavController().navigate(
                    R.id.action_taskList_to_taskDetail,
                    Bundle().apply { putInt(TASK_ID, it) })
            }
        }
    }

    private fun setUpObservers() {
        viewModel.fetchGetTasks()
        viewModel._task.observe(viewLifecycleOwner) { taskList ->
            taskList.let {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        binding.error.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        adapter.setItems(ArrayList(it.data))
                    }

                    Resource.Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.error.visibility = View.VISIBLE
                    }

                    Resource.Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.error.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setUpRetry() {
        binding.retry.setOnClickListener { viewModel.fetchGetTasks() }
    }
    companion object{
        const val TASK_ID = "taskId"
    }
}