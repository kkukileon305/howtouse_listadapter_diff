package com.goodness.toooodolost

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TodoViewModel(application: Application) : AndroidViewModel(application) {
	private val _todoList = MutableLiveData<List<Todo>>(
		emptyList()
	)

	val todolist: LiveData<List<Todo>>
		get() = _todoList

	fun addTodo(title: String, desc: String) {
		val newId = (_todoList.value?.maxOfOrNull { it.id } ?: 0) + 1

		_todoList.value = _todoList.value?.plus(
			Todo(newId, title, desc, false)
		)
	}

	fun toggleTodo(id: Long) {
		_todoList.value = _todoList.value?.map { todo -> if (todo.id == id) todo.copy(isDone = !todo.isDone) else todo }
	}
}