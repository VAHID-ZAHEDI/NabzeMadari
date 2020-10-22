package com.example.pregnancykotlin.login.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.main.view.MainActivity
import com.example.pregnancykotlin.utilities.Dialogs
import com.example.pregnancykotlin.utilities.Ui
import kotlinx.android.synthetic.main.fragment_validate.*


class ValidateFragment : Fragment() {
    private val args: ValidateFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_validate, container, false)
        Log.d("mmm", "onCreateView: ${args.phoneNumber}")

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_info.text =
            Ui.setBoldSpannable(
                "ما کد را برای  ${args.phoneNumber} ارسال کردیم\n کد را وارد کنید ",
                15,
                26
            )

        pv_otp.setOtpCompletionListener {
            val loginViewModel =
                DaggerInstanceComponent.builder().build()
            loginViewModel.getLoginViewModel()
                .validateCode(args.phoneNumber, it.toString())
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> Dialogs.showLoadingDialog(activity as Context)
                        Status.SUCCESS -> {
                            Dialogs.dismissLoadingDialog()
                            Log.d("zzx", "onViewCreated: ${it.data!!.isRegister}")
                            if (it.data!!.isRegister) {

                                loginViewModel.getLoginViewModel().login(args.phoneNumber)
                                    .observe(viewLifecycleOwner, Observer {
                                        Log.d("accessT", "onViewCreated: ${it.data?.accessToken}")
                                        startActivity(Intent(activity, MainActivity::class.java))
                                        activity?.finish()
                                    })
                            } else {
                                val action =
                                    ValidateFragmentDirections.actionValidateFragmentToSignUpUserFragment(
                                        args.phoneNumber
                                    )
                                Navigation.findNavController(view).navigate(action)
                            }


                        }
                        Status.ERROR -> {
                            Log.d("uuu", "onViewCreated: ${it.error?.httpErrorCode}")
                            Toast.makeText(context, it.error?.message, Toast.LENGTH_LONG).show()
                            pv_otp.clearComposingText()
                            Dialogs.dismissLoadingDialog()

                        }
                    }
                })

        }
    }

}