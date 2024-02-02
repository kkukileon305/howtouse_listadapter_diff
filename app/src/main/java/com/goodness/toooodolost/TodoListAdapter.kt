package com.goodness.toooodolost

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.goodness.toooodolost.databinding.TodoItemBinding

class TodoListAdapter(
	private val context: Activity,
	private val todoViewModel: TodoViewModel,
	private val updateTodoActivityResultLauncher: ActivityResultLauncher<Intent>? = null
) :
	ListAdapter<Todo, TodoListAdapter.TodoHolder>(TodoDiffCallback()) {
	inner class TodoHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(data: Todo) {
			binding.tvTitle.text = data.title
			binding.swIsDone.isChecked = data.isDone
			binding.tvDesc.text = data.desc

			binding.tvTitle.setOnClickListener {
				updateTodoActivityResultLauncher?.launch(Intent(context, DetailActivity::class.java).apply {
					putExtra(DetailActivity.ID, data)
				})
			}

			binding.swIsDone.setOnClickListener {
				todoViewModel.updateIsDone(data.id, !data.isDone)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, position: Int): TodoHolder {
		val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return TodoHolder(binding)
	}

	override fun onBindViewHolder(holder: TodoHolder, position: Int) {
		val todo = getItem(position)

		holder.bind(todo)
	}
}