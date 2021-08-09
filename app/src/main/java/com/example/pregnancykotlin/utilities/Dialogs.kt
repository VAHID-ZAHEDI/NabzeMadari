package com.example.pregnancykotlin.utilities

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.example.pregnancykotlin.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.labters.lottiealertdialoglibrary.DialogTypes
import com.labters.lottiealertdialoglibrary.LottieAlertDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_photo_view.*

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
        var et_comment: EditText
        var onSendComment: MutableLiveData<String> = MutableLiveData()
        val builder: MaterialAlertDialogBuilder = MaterialAlertDialogBuilder(context)
        var view = LayoutInflater.from(context).inflate(R.layout.dialog_comment, null, false)
        builder.setView(view)
        et_comment = view.findViewById(R.id.et_comment)
        builder.setPositiveButton("ثبت دیدگاه", DialogInterface.OnClickListener { _, _ ->
            onSendComment.value = et_comment.text.toString()
        })
        builder.setNegativeButton("بستن") { _, _ ->

        }
        builder.show()
        return onSendComment
    }

    fun showResourceDialog(context: Context) {
        var builder: AlertDialog.Builder = AlertDialog.Builder(context)
        var view = LayoutInflater.from(context).inflate(R.layout.dialog_resource, null, false)

        builder.setView(view)
        builder.setIcon(R.drawable.ic_books_stack_of_three)

        builder.setMessage(R.string.resources)

        builder.setNegativeButton("بستن") { dialogInterface, which ->

        }
        dialog = builder.create()
        dialog.setCancelable(true)
        dialog.show()
    }

    @SuppressLint("ResourceType")
    fun showPictureDialog(context: Context, img: String) {
        var dialog = Dialog(context)
        dialog.setContentView(R.layout.dialog_photo_view)
        Picasso.get().load(img).into(dialog.pv_img)
        dialog.setCancelable(true)
        var window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        );
//        window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window?.setBackgroundDrawable(ColorDrawable(R.color.black_transparent));
        dialog.show()
    }
}