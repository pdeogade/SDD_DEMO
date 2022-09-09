package com.example.todoapp.presentation.taskdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoapp.databinding.TaskDetailFragmentBinding
import com.example.todoapp.presentation.tasks.TaskListFragment
import com.example.todoapp.presentation.tasks.TaskListFragment.Companion.TASK_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailFragment : Fragment() {
    private lateinit var binding: TaskDetailFragmentBinding
    private val viewModel: TaskDetailViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TaskDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTaskDetail(arguments?.get(TASK_ID) as Int)
        viewModel.taskItem.observe(viewLifecycleOwner){
            binding.taskItem = it
        }
    }
}