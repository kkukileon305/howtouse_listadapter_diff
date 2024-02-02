package com.goodness.toooodolost

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.goodness.toooodolost.databinding.ActivityAddTodoBinding
import com.goodness.toooodolost.databinding.ActivityMainBinding

class AddTodo : AppCompatActivity() {
	private val binding by lazy { ActivityAddTodoBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		with(binding) {
			btnAddTodo.setOnClickListener {
				val title = etTitle.text.toString()
				val desc = etDesc.text.toString()

				if (title.isNotEmpty() && desc.isNotEmpty()) {
					val resultIntent = Intent().apply {
						putExtra("title", title)
						putExtra("desc", desc)
					}
					setResult(Activity.RESULT_OK, resultIntent)
					finish()
				} else {
					Toast.makeText(this@AddTodo, "제목과 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
				}
			}
		}
	}
}