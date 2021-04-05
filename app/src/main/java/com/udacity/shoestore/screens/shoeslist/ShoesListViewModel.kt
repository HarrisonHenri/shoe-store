package com.udacity.shoestore.screens.shoeslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesListViewModel : ViewModel() {

    private val _shoes = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    private val _navigateToShoeDetails = MutableLiveData<Boolean>()
    val navigateToShoeDetails: LiveData<Boolean>
        get() = _navigateToShoeDetails

    init {
        _navigateToShoeDetails.value = null
    }

    fun onGoToShoeDetailsClick () {
        _navigateToShoeDetails.value = true
    }

    fun onNavigationComplete () {
        _navigateToShoeDetails.value = null
    }

    fun saveCurrentDetail(detail: Shoe?) {
        detail?.let { shoe ->
            _shoes.value?.add(shoe)
        }
    }
}
