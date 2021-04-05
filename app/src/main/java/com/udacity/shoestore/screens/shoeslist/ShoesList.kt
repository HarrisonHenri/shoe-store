package com.udacity.shoestore.screens.shoeslist

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding


class ShoesList : Fragment() {

    private lateinit var binding: FragmentShoesListBinding
    private val args: ShoesListArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoes_list, container, false)


        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newShoe = args.newShoe
        val shoesListViewModel = ViewModelProvider(requireActivity()).get(ShoesListViewModel::class.java)
        if (newShoe != null){
            shoesListViewModel.saveCurrentDetail(newShoe)
        }
        
        binding.shoesListModel = shoesListViewModel

        binding.lifecycleOwner = this

        shoesListViewModel.navigateToShoeDetails.observe(
            viewLifecycleOwner,
            Observer { shouldGoToDetails ->
                shouldGoToDetails?.let {
                    val action = ShoesListDirections.actionShoesListToShoesDetails()
                    NavHostFragment.findNavController(this).navigate(action)
                    shoesListViewModel.onNavigationComplete()
                }
            })

        shoesListViewModel.shoes.observe(viewLifecycleOwner, Observer {
            context?.let { context ->
                val shoesListContainer = binding.shoeList
                it.forEach { shoe ->
                    val shoeLayout = ShoeItem(context)
                    shoeLayout.loadShoe(shoe)
                    shoesListContainer.addView(shoeLayout)
                }
            }
        })
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