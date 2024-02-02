package com.goodness.toooodolost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodness.toooodolost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val adapter by lazy { TodoListAdapter(this) }

	private val viewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

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
			val etTitle = binding.etTitle
			val etDesc = binding.etDesc

			val title = etTitle.text.toString()
			val desc = etDesc.text.toString()

			if (title.isNotEmpty() && desc.isNotEmpty()) {
				viewModel.addTodo(title, desc)
			} else {
				Toast.makeText(this@MainActivity, "제목과 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
			}

			etTitle.setText("")
			etDesc.setText("")
		}
	}
}