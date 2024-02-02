package com.goodness.toooodolost

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodness.toooodolost.databinding.FragmentTodoListBinding


class TodoListFragment : Fragment() {
	private lateinit var binding: FragmentTodoListBinding

	private val todoViewModel by lazy { ViewModelProvider(requireActivity())[TodoViewModel::class.java] }

	private val addTodoActivityResultLauncher =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == AppCompatActivity.RESULT_OK) {
				val data: Intent? = result.data
				val title = data?.getStringExtra("title")
				val desc = data?.getStringExtra("desc")

				if (!title.isNullOrEmpty() && !desc.isNullOrEmpty()) {
					todoViewModel.addTodo(title, desc)
				}
			}
		}

	private val updateTodoActivityResultLauncher =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == AppCompatActivity.RESULT_OK) {
				val data: Intent? = result.data
				val id = data?.getStringExtra("id")?.toLongOrNull()
				val title = data?.getStringExtra("title")
				val desc = data?.getStringExtra("desc")

				if (!title.isNullOrEmpty() && !desc.isNullOrEmpty() && id != null) {
					todoViewModel.updateTodoContent(id, title, desc)
				}
			} else if (result.resultCode == AppCompatActivity.RESULT_CANCELED) {
				val data: Intent? = result.data
				val id = data?.getStringExtra("id")?.toLongOrNull()

				if (id != null) {
					todoViewModel.deleteTodoContent(id)
				}
			}
		}

	private val adapter by lazy {
		TodoListAdapter(
			requireActivity(), todoViewModel, updateTodoActivityResultLauncher
		)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentTodoListBinding.inflate(inflater)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initList()
		initViewModel()
		initHandler()
	}

	private fun initList() {
		val listView = binding.rvTodos

		listView.layoutManager = LinearLayoutManager(requireActivity())
		listView.adapter = adapter
	}

	private fun initViewModel() {
		todoViewModel.todolist.observe(requireActivity()) {
			adapter.submitList(it)
		}
	}

	private fun initHandler() {
		binding.btnAddTodo.setOnClickListener {
			addTodoActivityResultLauncher.launch(Intent(requireActivity(), AddTodo::class.java))
		}
	}
}