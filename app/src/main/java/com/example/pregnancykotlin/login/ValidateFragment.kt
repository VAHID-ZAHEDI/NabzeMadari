package com.example.pregnancykotlin.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
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
import com.example.pregnancykotlin.di.component.DaggerPregnancyComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.login.remote.Resource
import com.example.pregnancykotlin.main.MainActivity
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
            DaggerPregnancyComponent.builder().setContext(activity as Context).build()
                .getLoginViewModel()
                .validateCode(args.phoneNumber, it.toString())
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Status.LOADING -> Dialogs.showLoadingDialog(activity as Context)
                        Status.SUCCESS -> {
                            Dialogs.dismissLoadingDialog()
                            Log.d("bvbvb", "Success: ${it.data?.accessToken}")
                            val action =
                                ValidateFragmentDirections.actionValidateFragmentToSignUpUserFragment()
                            Navigation.findNavController(view).navigate(action)

                        }
                        Status.ERROR -> {
                            Log.d("uuu", "onViewCreated: ${it.message?.httpErrorCode}")
                            Dialogs.dismissLoadingDialog()

                        }
                    }
                })

        }
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ValidateFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ValidateFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}