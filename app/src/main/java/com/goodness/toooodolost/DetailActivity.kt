package com.goodness.toooodolost

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.goodness.toooodolost.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
	private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

	private val viewModel by lazy { ViewModelProvider(this)[TodoViewModel::class.java] }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		init()
	}

	private fun init() {
		val data = intent.getParcelableExtra<Todo>(ID)

		binding.etTitle.setText(data?.title)
		binding.etDesc.setText(data?.desc)

		binding.btnAddTodo.setOnClickListener {
			val title = binding.etTitle.text.toString()
			val desc = binding.etDesc.text.toString()

			if (title.isNotEmpty() && desc.isNotEmpty()) {
				val resultIntent = Intent().apply {
					putExtra("id", data?.id.toString())
					putExtra("title", title)
					putExtra("desc", desc)
				}
				setResult(Activity.RESULT_OK, resultIntent)
				finish()
			} else {
				Toast.makeText(this@DetailActivity, "제목과 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
			}
		}

		binding.btnDeleteTodo.setOnClickListener {
			val resultIntent = Intent().apply {
				putExtra("id", data?.id.toString())
			}
			setResult(Activity.RESULT_CANCELED, resultIntent)
			finish()
		}
	}

	companion object {
		const val ID = "ID"
	}
}