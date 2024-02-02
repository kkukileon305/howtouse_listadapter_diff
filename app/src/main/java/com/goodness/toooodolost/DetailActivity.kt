package com.goodness.toooodolost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
	}

	companion object {
		const val ID = "ID"
	}
}