package com.kotlin.learn.core.utilities

import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING
import java.util.regex.Pattern

fun EditText.validateInput(length: Int, field: String): Pair<Boolean, String> {
    return if (this.text.trim().isEmpty() && this.text.trim().isBlank()) {
        Pair(false, "Invalid input your $field")
    } else if (this.text.trim().length < length) {
        Pair(false, "Invalid length $field, the minimum length of this field is $length")
    } else Pair(true, EMPTY_STRING)
}

fun EditText.validateEmail(length: Int, field: String): Pair<Boolean, String> {
    return if (this.text.trim().isEmpty() && this.text.trim().isBlank()) {
        Pair(false, "Invalid input your $field")
    } else if (this.text.trim().length < length) {
        Pair(false, "Invalid length $field, the minimum length of this field is $length")
    } else if (!Patterns.EMAIL_ADDRESS.matcher(this.text).matches()) {
        Pair(false, "Invalid format field $field")
    } else Pair(true, EMPTY_STRING)
}

fun EditText.validatePhone(length: Int, field: String): Pair<Boolean, String> {
    return if (this.text.trim().isEmpty() && this.text.trim().isBlank()) {
        Pair(false, "Invalid input your $field")
    } else if (this.text.trim().length < length) {
        Pair(false, "Invalid length $field, the minimum length of this field is $length")
    } else if (!Pattern.compile("^08[0-9]{9,}\$").matcher(this.text).matches()) {
        Pair(false, "Invalid format field $field")
    } else Pair(true, EMPTY_STRING)
}

fun isValidEmail(target: CharSequence): Boolean {
    return if (TextUtils.isEmpty(target)) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}