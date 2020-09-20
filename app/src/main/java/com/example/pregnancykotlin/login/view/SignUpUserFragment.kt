package com.example.pregnancykotlin.login.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status

import com.example.pregnancykotlin.models.User
import kotlinx.android.synthetic.main.fragment_sign_up_user.*


class SignUpUserFragment : Fragment() {
    private val args: SignUpUserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_register.setOnClickListener {
            val loginViewModel = DaggerInstanceComponent.builder().build().getLoginViewModel()
            loginViewModel.signUp(
                User(
                    et_firstName.text.toString(),
                    et_lastName.text.toString(),
                    22,
                    args.phoneNumber
                )
            )
                .observe(viewLifecycleOwner, Observer {
                    Log.d("bbb", "onViewCreated: ${it.status}")
                    when (it.status) {
                        Status.SUCCESS -> {
                            loginViewModel.login(args.phoneNumber).observe(viewLifecycleOwner,
                                Observer {
                                    when (it.status) {
                                        Status.SUCCESS -> {
                                            val action =
                                                SignUpUserFragmentDirections.actionSignUpUserFragmentToMainFragment()
                                            Navigation.findNavController(view).navigate(action)
                                        }
                                    }
                                })
                        }
                    }
                })
        }
    }

}