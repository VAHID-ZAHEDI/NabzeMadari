package com.example.pregnancykotlin.login.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pregnancykotlin.GlobalVariables
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.utilities.Dialogs
import com.example.pregnancykotlin.utilities.Ui
import kotlinx.android.synthetic.main.fragment_validate.*


class ValidateFragment : Fragment() {
    private val args: ValidateFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_validate, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_info.text =
            Ui.setBoldSpannable(
                "ما کد را برای  ${args.phoneNumber} ارسال کردیم\n کد را وارد کنید ",
                15,
                26
            )
        val loginViewModel =
            DaggerInstanceComponent.builder().build().getLoginViewModel()
        pv_otp.setOtpCompletionListener {

            loginViewModel
                .validateCode(args.phoneNumber, it.toString())
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> Dialogs.showLoadingDialog(activity as Context)
                        Status.SUCCESS -> {
                            Dialogs.dismissLoadingDialog()
                            if (it.data!!.isRegister) {
                                loginViewModel.login(args.phoneNumber)
                                    .observe(viewLifecycleOwner, {
                                        Log.d("accessT", "onViewCreated: ${it.data?.accessToken}")
                                        if (it.data?.accessToken != null) {
                                            val direction =
                                                ValidateFragmentDirections.actionValidateFragmentToMainActivity()
                                            findNavController().navigate(direction)
                                            var msp = DaggerPregnancyComponent.builder()
                                                .setContext(activity as Context)
                                                .build().getSafePref()
                                            msp.setSharedPreferences(
                                                GlobalVariables.TOKEN,
                                                "Bearer ${it.data.accessToken}"
                                            )
                                            msp.setSharedPreferences(
                                                GlobalVariables.HAS_SIGN_IN,
                                                true
                                            )
                                            msp.setSharedPreferences(
                                                GlobalVariables.PHONE_NUMBER,
                                                args.phoneNumber
                                            )
                                            activity?.finish()
                                        }
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