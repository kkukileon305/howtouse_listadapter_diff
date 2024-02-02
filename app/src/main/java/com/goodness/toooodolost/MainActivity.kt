package com.goodness.toooodolost

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodness.toooodolost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val adapter by lazy { TodoListAdapter(this) }

	private val viewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

	private val addTodoActivityResultLauncher =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == RESULT_OK) {
				val data: Intent? = result.data
				val title = data?.getStringExtra("title")
				val desc = data?.getStringExtra("desc")

				if (!title.isNullOrEmpty() && !desc.isNullOrEmpty()) {
					viewModel.addTodo(title, desc)
				}
			}
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContentView(binding.root)
		initList()
		initViewModel()
		initHandler()
	}

	private fun initList() {
		val listView = binding.rvTodos

		listView.layoutManager = LinearLayoutManager(this)
		listView.adapter = adapter
	}

	private fun initViewModel() {
		viewModel.todolist.observe(this) {
			adapter.submitList(it)
		}
	}

	private fun initHandler() {
		binding.btnAddTodo.setOnClickListener {
			addTodoActivityResultLauncher.launch(Intent(this, AddTodo::class.java))
		}
	}
}