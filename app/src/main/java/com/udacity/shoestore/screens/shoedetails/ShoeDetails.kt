package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding

class ShoeDetails : Fragment() {

    private lateinit var shoeDetailsViewModel: ShoeDetailsViewModel
    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)

        binding.lifecycleOwner = this

        shoeDetailsViewModel = ViewModelProvider(this).get(ShoeDetailsViewModel::class.java)

        shoeDetailsViewModel.navigateToShoesList.observe(viewLifecycleOwner, Observer { shouldGoBack ->
            shouldGoBack?.let {
                if (shouldGoBack) {
                    val action = ShoeDetailsDirections.actionShoeDetailsToShoesList(shoeDetailsViewModel.shoe.value)
                    NavHostFragment.findNavController(this).navigate(action)
                    shoeDetailsViewModel.onNavigationComplete()
                }
            }
        })

        binding.shoeDetailsModel = shoeDetailsViewModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}