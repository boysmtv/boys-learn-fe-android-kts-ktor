package com.kotlin.learn.core.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kotlin.learn.core.common.R
import com.kotlin.learn.core.common.util.network.NetworkConnectionLiveData
import com.kotlin.learn.core.common.util.network.debounce
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialog
import com.kotlin.learn.core.ui.dialog.base.BaseDataDialogGeneral
import com.kotlin.learn.core.ui.dialog.common.DialogGeneralError
import com.kotlin.learn.core.ui.dialog.common.DialogNoInternet
import com.kotlin.learn.core.ui.dialog.common.DialogWithAction
import com.kotlin.learn.core.ui.dialog.common.ProgressBarDialog
import com.kotlin.learn.core.ui.util.showDialog
import com.kotlin.learn.core.utilities.Constant.EMPTY_STRING

abstract class BaseFragment<T : ViewBinding>
    (private val bindingInflater: (layoutInflater: LayoutInflater) -> T) : Fragment() {

    var binding: T by viewBinding()

    protected abstract fun setupView()

    private var onReconnect: (() -> Unit)? = null

    private var dialog: ProgressBarDialog? = null
    private var dialogGeneralError: DialogGeneralError? = null
    private lateinit var dialogNoInternet: DialogNoInternet
    private var needToShowErrorConnection = false
    private var isConnectionAvailable = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupCheckConnection()
    }

    private fun setupCheckConnection(){
        val connectionLiveData = NetworkConnectionLiveData(requireContext())
        connectionLiveData.debounce().observe(viewLifecycleOwner) { isConnected ->
            isConnected?.let {
                needToShowErrorConnection = !it
                isConnectionAvailable = it
                if (::dialogNoInternet.isInitialized) dialogNoInternet.dismiss()
                if (it) onReconnect?.let { action -> action() } else showErrorNoInternetConnection()
            }
        }
    }

    fun showDialogWithActionButton(
        dataToDialog: BaseDataDialog,
        actionClickPrimary: () -> Unit,
        actionClickSecondary: (() -> Unit)? = null,
        tag: String? = EMPTY_STRING,
    ) {
        val dialog = DialogWithAction(
            onClickButtonPrimary = { actionClickPrimary() },
            onClickButtonSecondary = { actionClickSecondary?.invoke() }
        ).apply { data = dataToDialog }
        if (tag?.isNotEmpty() == true) dialog.show(childFragmentManager, tag)
        else childFragmentManager.showDialog(dialog)
    }

    fun showHideProgress(isLoading: Boolean) =
        if (isLoading) {
            dialog = ProgressBarDialog()
            dialog?.show(childFragmentManager, dialog?.tag)
        } else {
            dialog?.dismiss()
            dialog = null
        }

    private fun showGeneralError(
        data: BaseDataDialogGeneral,
        actionClick: () -> Unit,
        actionClickSecondary: () -> Unit,
    ) {
        dialogGeneralError = DialogGeneralError(
            data,
            actionClick,
            actionClickSecondary,
            onDismissDialogGeneralError()
        )
        dialogGeneralError?.show(childFragmentManager, tag)
    }

    fun showDialogGeneralError(title: String, message: String) {
        showGeneralError(
            BaseDataDialogGeneral(
                title = title,
                message = message,
                icon = R.drawable.ic_connection_error,
                textPrimaryButton = "OK, Close!",
                visibleBackToSplash = false,
                dismissOnAction = true
            ),
            actionClick = { dismissDialogGeneralError() }
        ) {}
    }

    private fun onDismissDialogGeneralError(): () -> Unit = {
        dialogGeneralError?.dismiss()
        dialogGeneralError = null
    }

    private fun dismissDialogGeneralError() {
        dialogGeneralError?.dismiss()
        dialogGeneralError = null
    }

    private fun showErrorNoInternetConnection() {
        dialogNoInternet = DialogNoInternet()
        dialogNoInternet.show(childFragmentManager, dialogNoInternet.tag)
    }

    override fun onResume() {
        super.onResume()
        if (::dialogNoInternet.isInitialized && isConnectionAvailable) dialogNoInternet.dismiss()
    }

    override fun onDestroyView() {
        dialog = null
        dialogGeneralError = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        dialog = null
        dialogGeneralError = null
        super.onDestroy()
    }

}