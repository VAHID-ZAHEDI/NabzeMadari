package com.example.pregnancykotlin.login.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.pregnancykotlin.R
import com.example.pregnancykotlin.di.component.DaggerInstanceComponent
import com.example.pregnancykotlin.enum.Status
import com.example.pregnancykotlin.login.LoginViewModel
import com.example.pregnancykotlin.utilities.Dialogs
import kotlinx.android.synthetic.main.fragment_phone_number.*


class PhoneNumberFragment : Fragment() {
//    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        loginViewModel = activity?.run {
//            ViewModelProviders.of(this)[LoginViewModel::class.java]
//        } ?: throw Exception("Invalid Activity")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        if (activity != null) {
////        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
//        }
//        loginViewModel=DaggerPregnancyComponent.builder().setContext(activity as Context).build().getLoginViewModel()

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
        val loginViewModel: LoginViewModel =
            DaggerInstanceComponent.builder().build().getLoginViewModel()
        bt_send.setOnClickListener(View.OnClickListener {
            var phoneNumber = et_phoneNumber.text.toString()
            loginViewModel!!.generateCode(phoneNumber.replace(" ", "")).observe(
                viewLifecycleOwner,
                Observer {
//                Toast.makeText(activity, it.data?.code, Toast.LENGTH_LONG).show()
                    when (it.status) {
                        Status.LOADING -> Dialogs.showLoadingDialog(activity as Context)
                        Status.SUCCESS -> {
                            Dialogs.dismissLoadingDialog()
//                            var args = Bundle()
//                            args.putString("code", it.data?.code)
//                            args.putString("phoneNumber", phoneNumber)
//                            val nav = activity?.findNavController(R.id.nav_host)
//                            nav?.navigate(R.id.validateFragment, args)
                            val action =
                                PhoneNumberFragmentDirections.navigateToValidateFragment(
                                    phoneNumber.replace(" ", "")
                                )
                            Navigation.findNavController(view).navigate(action)
//                            val viewpager = activity?.findViewById<ViewPager2>(R.id.vp_login)
//                            viewpager!!.currentItem++
                        }

                    }
                })
        })


    }

}