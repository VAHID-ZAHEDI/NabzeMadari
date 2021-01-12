package com.example.pregnancykotlin.utilities

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.pregnancykotlin.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        if (alertDialog != null && alertDialog.isShowing) {
            alertDialog.dismiss()
        }
    }

    fun showWriteCommentDialog(context: Context): MutableLiveData<String> {
        var et_comment:EditText
        var onSendComment: MutableLiveData<String> = MutableLiveData()
        val builder: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
        var view=LayoutInflater.from(context).inflate(R.layout.dialog_comment,null,false)
        builder.setView(view)
        et_comment=view.findViewById(R.id.et_comment)
        builder.setPositiveButton("ثبت دیدگاه", DialogInterface.OnClickListener { _, _ ->
            onSendComment.value =et_comment.text.toString()
        })
        builder.setNegativeButton("بستن") { _, _ ->

        }
        builder.show()
        return onSendComment
    }
}