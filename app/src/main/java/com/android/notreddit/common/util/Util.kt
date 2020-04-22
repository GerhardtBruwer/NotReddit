package com.android.notreddit.common.util

import android.content.Context
import android.widget.Toast
import com.android.notreddit.model.ApiError

fun Context.displayErrorMessage(errResponse: ApiError) {
    Toast.makeText(this, errResponse.message, Toast.LENGTH_LONG).show()
}

