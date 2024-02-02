package com.goodness.toooodolost

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodness.toooodolost.databinding.FragmentBookmarkBinding
import com.goodness.toooodolost.databinding.FragmentTodoListBinding


class BookmarkFragment : Fragment() {
	private lateinit var binding: FragmentBookmarkBinding

	private val viewModel by lazy { ViewModelProvider(requireActivity())[TodoViewModel::class.java] }
	private val adapter by lazy { TodoListAdapter(requireActivity(), viewModel) }

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentBookmarkBinding.inflate(inflater)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initList()
		initViewModel()
	}

	private fun initList() {
		val listView = binding.rvTodos

		listView.layoutManager = LinearLayoutManager(requireActivity())
		listView.adapter = adapter
	}

	private fun initViewModel() {
		viewModel.todolist.observe(requireActivity()) { todoList ->
			adapter.submitList(todoList.filter { it.isDone })
		}
	}
}