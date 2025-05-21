package com.kotlin.learn.core.ui.dialog.common

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.DialogFragment
import com.kotlin.learn.core.ui.databinding.DialogGeneralErrorBinding
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialogGeneral
import com.kotlin.learn.core.utilities.Constant.CLOSE_APPS_MESSAGE
import com.kotlin.learn.core.utilities.Constant.ONE_THOUSAND_FIFTY
import com.kotlin.learn.core.utilities.hide
import com.kotlin.learn.core.utilities.show

class DialogGeneralError(
    private val data: BaseDataDialogGeneral,
    private val onClickPrimaryButton: () -> Unit,
    private val onClickSecondaryButton: () -> Unit = {},
    private val onDismiss: () -> Unit = {}
) : DialogFragment() {
    lateinit var binding: DialogGeneralErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogGeneralErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(Color.GRAY.toDrawable().apply { alpha = ONE_THOUSAND_FIFTY })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
        isCancelable = data.isCancelable
    }

    private fun setupView() = with(binding) {
        tvTitleCommonDialogSingleButton.text = data.title
        tvContentCommonDialogSingleButton.text = data.message
        btnActionPrimaryCommonDialog.text = data.textPrimaryButton
        data.icon?.let { ivIconDialogWithAction.setImageResource(it) }

        btnActionPrimaryCommonDialog.text = data.textPrimaryButton
        if (data.secondaryIsVisible == true) {
            btnActionSecondaryCommonDialog.show()
            dialogGeneral.removeView(btnActionPrimaryCommonDialog)
            dialogGeneral.addView(btnActionPrimaryCommonDialog)
            btnActionSecondaryCommonDialog.text = data.textPrimaryButton
            btnActionPrimaryCommonDialog.text = CLOSE_APPS_MESSAGE
        } else btnActionSecondaryCommonDialog.hide()
    }

    private fun setupListeners() = with(binding) {
        if (data.secondaryIsVisible == true) {
            btnActionPrimaryCommonDialog.setOnClickListener { onClickSecondaryButton() }
            btnActionSecondaryCommonDialog.setOnClickListener {
                if (data.dismissOnAction) dismiss()
                onClickPrimaryButton()
            }
        } else {
            btnActionPrimaryCommonDialog.setOnClickListener {
                if (data.dismissOnAction) onDismiss()
                onClickPrimaryButton()
            }
            btnActionSecondaryCommonDialog.setOnClickListener { onClickSecondaryButton() }
        }
    }

}