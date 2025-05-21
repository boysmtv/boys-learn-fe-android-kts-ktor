package com.kotlin.learn.core.common.util

import android.app.AppOpsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class NotificationUtil(private val context: Context) {

    private val checkOpNoThrow = "checkOpNoThrow"
    private val onPostNotification = "OP_POST_NOTIFICATION"

    fun isNotificationEnabled(): Boolean {
        val mAppOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val appInfo: ApplicationInfo = context.applicationInfo
        val pkg: String = context.applicationContext.packageName
        val uid = appInfo.uid
        val appOpsClass: Class<*>?
        try {
            appOpsClass = Class.forName(AppOpsManager::class.java.name)
            val checkOpNoThrowMethod: Method = appOpsClass.getMethod(
                checkOpNoThrow, Integer.TYPE, Integer.TYPE,
                String::class.java
            )
            val opPostNotificationValue: Field = appOpsClass.getDeclaredField(onPostNotification)
            val value = opPostNotificationValue.get(Int::class.java) as Int
            return checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) as Int == AppOpsManager.MODE_ALLOWED
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
        return false
    }

}