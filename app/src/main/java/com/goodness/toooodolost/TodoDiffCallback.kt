package com.goodness.toooodolost

import androidx.recyclerview.widget.DiffUtil

class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {

	override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = oldItem === newItem

	override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
		return oldItem == newItem
	}
}