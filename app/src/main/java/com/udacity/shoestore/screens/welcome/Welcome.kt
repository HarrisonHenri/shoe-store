package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class Welcome : Fragment() {

    private lateinit var welcomeViewModel: WelcomeViewModel
    private lateinit var binding: FragmentWelcomeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        binding.lifecycleOwner = this

        welcomeViewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)

        welcomeViewModel.navigateToInstructions.observe(viewLifecycleOwner, Observer { shouldGoToInstructions ->
            shouldGoToInstructions?.let {
                val action = WelcomeDirections.actionWelcomeToInstructions()
                NavHostFragment.findNavController(this).navigate(action)
                welcomeViewModel.onNagivationComplete()
            }
        })

        binding.welcomeModel = welcomeViewModel

        return binding.root
    }
}