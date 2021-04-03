package com.udacity.shoestore.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.udacity.shoestore.R
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class Login : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.lifecycleOwner = this

        loginViewModel.navigateToWelcome.observe(viewLifecycleOwner, Observer { shouldGoToWelcome ->
            if (shouldGoToWelcome != null) {
                if (shouldGoToWelcome) {
                    val action = LoginDirections.actionLoginToWelcome()
                    findNavController(this).navigate(action)
                    loginViewModel.onNavigationComplete()
                } else {
                    Toast.makeText(context, "You are using the wrong credentials, please verify your data.", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.loginModel = loginViewModel

        return binding.root
    }
}