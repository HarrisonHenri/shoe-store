package com.udacity.shoestore.screens.instructions

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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class Instructions : Fragment() {

    private lateinit var instructionsViewModel: InstructionsViewModel
    private lateinit var binding: FragmentInstructionsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)

        binding.lifecycleOwner = this

        instructionsViewModel = ViewModelProvider(this).get(InstructionsViewModel::class.java)

        instructionsViewModel.navigateToShoesList.observe(viewLifecycleOwner, Observer { shouldGoToInstructions ->
            shouldGoToInstructions?.let {
                val action = InstructionsDirections.actionInstructionsToShoeList()
                NavHostFragment.findNavController(this).navigate(action)
                instructionsViewModel.onNavigationComplete()
            }
        })

        binding.instructionsModel = instructionsViewModel

        return binding.root
    }
}