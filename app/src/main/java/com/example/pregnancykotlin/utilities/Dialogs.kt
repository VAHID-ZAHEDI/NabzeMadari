package com.example.pregnancykotlin.utilities

import android.content.Context
import com.labters.lottiealertdialoglibrary.DialogTypes
import com.labters.lottiealertdialoglibrary.LottieAlertDialog

object Dialogs {
    private lateinit var alertDialog: LottieAlertDialog
    fun showLoadingDialog(context: Context) {
        alertDialog = LottieAlertDialog.Builder(context, DialogTypes.TYPE_CUSTOM, "loading.json")
            .setTitle("در حال ارسال")
            .setDescription("لطفا کمی صبر کنید")
            .build()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun dismissLoadingDialog() {
        if (alertDialog.isShowing) {
            alertDialog.dismiss()
        }
    }
}