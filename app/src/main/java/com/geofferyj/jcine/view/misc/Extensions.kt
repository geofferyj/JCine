package com.geofferyj.jcine.view.misc

import android.util.Log
import android.widget.TextView
import com.geofferyj.jcine.BuildConfig

// Internal extensions

internal inline val TextView.isEllipsized get() = layout?.getEllipsisCount(lineCount - 1) ?: 0 > 0

internal fun log(message: String) {
	if (BuildConfig.DEBUG)
		Log.d("ExpandableTextView", message)
}

internal fun doNothing() {}
