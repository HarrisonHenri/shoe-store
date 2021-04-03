package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _navigateToInstructions = MutableLiveData<Boolean>()
    val navigateToInstructions: LiveData<Boolean>
        get() = _navigateToInstructions

    init {
        _navigateToInstructions.value = null
    }

    fun onGoToInstructionsClick () {
        _navigateToInstructions.value = true
    }

    fun onNagivationComplete () {
        _navigateToInstructions.value = null
    }
}
