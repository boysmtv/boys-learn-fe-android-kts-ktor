package com.kotlin.learn.core.common.base

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kotlin.learn.core.common.util.listener.CommonListener
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialog
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialogGeneral
import com.kotlin.learn.core.ui.dialog.common.DialogGeneralError
import com.kotlin.learn.core.ui.dialog.common.DialogLogout
import com.kotlin.learn.core.ui.dialog.common.DialogWithAction
import com.kotlin.learn.core.utilities.Constant.ZERO

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    var dialogGeneralError: DialogGeneralError? = null

    var listener: CommonListener? = null
    var retryCount = ZERO
    var retryReinvoke = ZERO

    lateinit var binding: T
    protected abstract fun initBinding(): T
    protected abstract fun initView()
    abstract fun backToLogin()
    abstract fun backToSplash()
    abstract fun showBackButton(isShow: Boolean)
    abstract fun showTopBar(isShow: Boolean)
    abstract fun setTopBarTitle(title: String)
    abstract fun onBackNavigation(scenario: String?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        setContentView(binding.root)
        initView()
    }

    fun showDialogWithActionButton(
        dataToDialog: BaseDataDialog,
        actionClickPrimary: () -> Unit,
        actionClickSecondary: () -> Unit,
        tag: String
    ) {
        DialogWithAction(
            onClickButtonPrimary = { actionClickPrimary() },
            onClickButtonSecondary = { actionClickSecondary() }
        ).apply { data = dataToDialog }.show(supportFragmentManager, tag)
    }

    fun showDialogWithThreeActionButton(
        dataToDialog: BaseDataDialog,
        actionClickPrimary: () -> Unit,
        actionClickSecondary: () -> Unit,
        tag: String
    ) {
        DialogLogout(
            onClickButtonPrimary = { actionClickPrimary() },
            onClickButtonSecondary = { actionClickSecondary() },
            onClickButtonBack = { },
            data = dataToDialog
        ).show(supportFragmentManager, tag)
    }

    open fun errorHandler(code: String?, title: String? = null, message: String? = null) = Unit

    fun showSseGeneralError(
        data: BaseDataDialogGeneral,
        actionClick: () -> Unit,
        actionClickSecondary: () -> Unit
    ) {
        dialogGeneralError = DialogGeneralError(
            data,
            actionClick,
            actionClickSecondary,
            onDismissDialogGeneralError()
        )
        dialogGeneralError = DialogGeneralError(data, actionClick, actionClickSecondary)
        dialogGeneralError?.show(supportFragmentManager, DialogGeneralError::class.simpleName)
    }

    private fun onDismissDialogGeneralError(): () -> Unit = {
        dialogGeneralError?.dismiss()
        dialogGeneralError = null
    }
}