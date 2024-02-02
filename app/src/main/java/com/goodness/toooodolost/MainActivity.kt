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
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val adapter = ViewPagerAdapter(this)
		val pager = binding.viewPager
		val tabLayout = binding.tab

		pager.adapter = adapter

		TabLayoutMediator(tabLayout, pager) { tab, position ->
			tab.text = when (position) {
				0 -> "Todo"
				1 -> "Bookmark"
				else -> ""
			}
		}.attach()

		setContentView(binding.root)
	}
}