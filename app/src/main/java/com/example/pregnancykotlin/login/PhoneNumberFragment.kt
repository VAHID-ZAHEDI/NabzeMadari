package com.example.pregnancykotlin.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.utilities.Dialogs
import kotlinx.android.synthetic.main.fragment_phone_number.*


class PhoneNumberFragment : Fragment() {
    var loginViewModel: LoginViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt_send.setOnClickListener(View.OnClickListener {
            loginViewModel = LoginViewModel()
            var phoneNumber = et_phoneNumber.text.toString()
            loginViewModel!!.generateCode(phoneNumber.replace(" ", "")).observe(
                viewLifecycleOwner,
                Observer {
//                Toast.makeText(activity, it.data?.code, Toast.LENGTH_LONG).show()
                    when (it.status) {
                        Status.LOADING -> Dialogs.showLoadingDialog(activity as Context)
                        Status.SUCCESS -> {
                            Dialogs.dismissLoadingDialog()
                            Log.d("aaa", "onViewCreated: " + it.data?.code)

                        }

                    }
                })
        })
    }

}