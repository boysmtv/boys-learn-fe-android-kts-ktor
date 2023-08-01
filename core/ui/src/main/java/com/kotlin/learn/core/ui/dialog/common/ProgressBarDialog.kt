package com.kotlin.learn.core.ui.dialog.common

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.kotlin.learn.core.ui.databinding.DialogProgressbarBinding

class ProgressBarDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val lp = dialog.window?.attributes
        lp?.gravity = Gravity.CENTER

        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.attributes = lp
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogProgressbarBinding.inflate(inflater, container, false).root
    }
}