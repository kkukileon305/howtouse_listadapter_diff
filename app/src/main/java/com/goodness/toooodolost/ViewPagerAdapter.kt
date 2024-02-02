package com.goodness.toooodolost

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: MainActivity) : FragmentStateAdapter(fragment) {
	private val fragments by lazy {
		listOf(
			TodoListFragment(),
			BookmarkFragment()
		)
	}

	override fun getItemCount(): Int = fragments.size

	override fun createFragment(position: Int): Fragment {
		return fragments[position]
	}
}