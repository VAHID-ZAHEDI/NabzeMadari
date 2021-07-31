package com.example.pregnancykotlin.utilities

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.example.pregnancykotlin.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.labters.lottiealertdialoglibrary.DialogTypes
import com.labters.lottiealertdialoglibrary.LottieAlertDialog

object Dialogs {
    private lateinit var alertDialog: LottieAlertDialog
    private lateinit var dialog: AlertDialog
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
    fun showResourceDialog(context :Context){
         var builder :AlertDialog.Builder=AlertDialog.Builder(context)
        var view=LayoutInflater.from(context).inflate(R.layout.dialog_resource,null,false)

        //set title for alert dialog
        builder.setView(view)
        builder.setTitle(R.string.titlealert)
        //set message for alert dialog
        builder.setIcon(R.drawable.ic_books_stack_of_three)

        builder.setNegativeButton("No"){dialogInterface, which ->
            Toast.makeText(context,"clicked No", Toast.LENGTH_LONG).show()
        }
        // Create the AlertDialog
        // Set other dialog properties
        dialog=builder.create()
        dialog.setCancelable(true)
        dialog.show()
    }
}