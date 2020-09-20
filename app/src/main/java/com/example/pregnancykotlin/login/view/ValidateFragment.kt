package com.example.pregnancykotlin.login.view

import android.content.Context
import android.content.Intent
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "code"
private const val ARG_PARAM2 = "phoneNumber"

/**
 * A simple [Fragment] subclass.
 * Use the [ValidateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ValidateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val args: ValidateFragmentArgs by navArgs()
//    private var param1: String? = null
//    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_validate, container, false)
        Log.d("mmm", "onCreateView: ${args.phoneNumber}")
//        tv_info.text="ما کد تایید را برای شماره‌ی ارسال کردیم"

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Log.d("mbmvmbm", "onViewCreated: $param1")
//        Log.d("mbmvmbm", "onViewCreated: $param2")
//        tv_info.text = args.phoneNumber
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