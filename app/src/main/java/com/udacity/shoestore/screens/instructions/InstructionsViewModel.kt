package com.udacity.shoestore.screens.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {

    private val _navigateToShoesList = MutableLiveData<Boolean>()
    val navigateToShoesList: LiveData<Boolean>
        get() = _navigateToShoesList

    init {
        _navigateToShoesList.value = null
    }

    fun onGoToShoesListClick () {
        _navigateToShoesList.value = true
    }

    fun onNavigationComplete () {
        _navigateToShoesList.value = null
    }
}
