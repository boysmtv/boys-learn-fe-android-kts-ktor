package com.kotlin.learn.core.nav.utils

import android.net.Uri
import com.kotlin.learn.core.nav.utils.StateHelper.CustomerScreen.TRANSACTION_OVERVIEW_CUSTOMER
import com.kotlin.learn.core.nav.utils.StateHelper.GbScreen.CUSTOMER_INFORMATION_VERIFICATION
import com.kotlin.learn.core.nav.utils.StateHelper.GbScreen.TRANSACTION_OVERVIEW_GB
import com.kotlin.learn.core.nav.utils.StateHelper.UriConstants.AUTHORITY
import com.kotlin.learn.core.nav.utils.StateHelper.UriConstants.SCHEME
import com.kotlin.learn.core.utilities.ArgumentConstants.Common.ARGS_KEY_SCENARIO
import com.kotlin.learn.core.utilities.ArgumentConstants.Transaction.SCENARIO_TRANSACTION_SUBMIT
import com.kotlin.learn.core.utilities.ConstantDeeplink.OTP_CUSTOMER_WAIT_PAGE
import com.kotlin.learn.core.utilities.ConstantDeeplink.TRANSACTION_OVERVIEW_PAGE

object StateHelper {

    fun getStateDestination(state: String, isCustomer: Boolean = false): Uri.Builder? =
        if (isCustomer) onCustomerState(state)
        else onGbState(state)

    private fun onCustomerState(state: String) =
        when (state) {
            TRANSACTION_OVERVIEW_PAGE -> buildUri(TRANSACTION_OVERVIEW_CUSTOMER)
            else -> null
        }

    private fun onGbState(state: String) =
        when (state) {
            TRANSACTION_OVERVIEW_PAGE -> buildUri(TRANSACTION_OVERVIEW_GB)
            else -> null
        }

    fun mapCustomerStatusScenario(state: String, uri: Uri.Builder): Uri.Builder =
        when (state) {
            OTP_CUSTOMER_WAIT_PAGE ->
                uri.appendQueryParameter(ARGS_KEY_SCENARIO, SCENARIO_TRANSACTION_SUBMIT)
            else -> uri
        }

    private fun buildUri(path: String) =
        Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(path)

    object UriConstants {
        const val SCHEME = "bmri.id"
        const val AUTHORITY = "nbds"
    }

    object CustomerScreen {
        const val TRANSACTION_OVERVIEW_CUSTOMER = "transaction-overview-customer"
        const val TRANSACTION_CONFIRMATION_CUSTOMER = "transaction-confirmation-customer"
        const val TRANSACTION_RECEIPT = "transaction-receipt"
        const val TRANSACTION_EDIT = "transaction-edit"
        const val WAITING_ADVERTISEMENT_SUPERVISOR = "waiting-advertisement-supervisor"
        const val WAITING_CUSTOMER_SCREEN_STATUS = "waiting-customer-screen-status"
        const val WAITING_CUSTOMER_LANDING = "waiting-customer-landing"
        const val RATING_SERVICE = "rating-service"
        const val NUMERIC_AUTH_VERIFICATION_LANDING = "numeric-authorization-verification-landing"
    }

    object GbScreen {
        const val TRANSACTION_OVERVIEW_GB = "transaction-overview-gb"
        const val TRANSACTION_CONFIRMATION_GB = "transaction-confirmation-gb"
        const val CUSTOMER_INFORMATION_VERIFICATION = "customer-information-verification"
        const val SUPERVISOR_CONFIRMATION = "supervisor-confirmation"
        const val OLD_BDS_REVIEW = "old-bds-review"
        const val WAITING_TRANSITION = "waiting-transition"
        const val NUMERIC_AUTH_OTP = "numeric-auth-otp"
    }
}