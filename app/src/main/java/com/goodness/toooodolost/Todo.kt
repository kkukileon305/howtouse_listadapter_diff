package com.goodness.toooodolost

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
	val id: Long,
	val title: String,
	val desc: String
) : Parcelable
